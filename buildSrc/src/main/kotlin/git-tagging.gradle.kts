import org.gradle.api.tasks.Exec

tasks.register<Exec>("createVersionTag") {
    group = "versioning"
    description = "Cria uma tag Git baseada na versão definida no gradle.properties do módulo."

    dependsOn("clean")

    commandLine("bash", "-c", """
        # Lê a versão e o nome do projeto diretamente do Gradle
        TAG_NAME="${project.name}-v${project.property("library.version")}"

        echo "Verificando se a tag ${'$'}TAG_NAME já existe..."

        # Verifica se a tag já existe localmente ou remotamente
        if git rev-parse "${'$'}TAG_NAME" >/dev/null 2>&1; then
            echo "A tag ${'$'}TAG_NAME já existe. Nenhuma ação foi tomada."
            exit 0
        else
            echo "Criando a tag ${'$'}TAG_NAME..."
            git tag "${'$'}TAG_NAME"
            echo "Tag ${'$'}TAG_NAME criada com sucesso."
        fi
    """)
}