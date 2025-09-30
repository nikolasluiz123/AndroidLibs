# 📚 AndroidLibs

Coleção de bibliotecas modulares para acelerar o desenvolvimento de aplicativos Android modernos.  
Cada módulo foi projetado para ser independente, reutilizável e facilmente integrável em diferentes projetos.

---

## 📑 Sumário

- [🔧 core-utils](#-core-utils)  
- [📱 core-android-utils](#-core-android-utils)  
- [🎨 core-android-compose-utils](#-core-android-compose-utils)  
- [📄 android-pdf-generator](#-android-pdf-generator)  
- [📊 compose-charts](#-compose-charts)  
- [🔥 firebase-toolkit](#-firebase-toolkit)  
- [❤️ health-connect-toolkit](#-health-connect-toolkit)  
- [🎛 ui-compose-components](#-ui-compose-components)  
- [⚙️ work-manager-toolkit](#️-work-manager-toolkit)
- [⚡Instalação](#-instalação)

---

## 🔧 core-utils

Utilitários em **Kotlin puro** (sem dependências do Android).  
Focado em lógica de negócios, manipulação de dados e extensões comuns.

### Funcionalidades
- Segurança com **Password Hashing** (PBKDF2 + HMAC-SHA256).  
- **Serialização JSON** com Gson + suporte a `java.time`.  
- **Funções de extensão** para strings, datas, números e JSON.  
- Padrões consistentes de formatação de data/hora via **EnumDateTimePatterns**.  

---

## 📱 core-android-utils

Extensões do `core-utils` com dependências Android: arquivos, vídeos, notificações e contexto.

### Funcionalidades
- **Gerenciamento de Arquivos e Vídeos** (criação, cópia, metadados e thumbnails).  
- **Compressão de Vídeo** com Jetpack Media3.  
- **Notificações Simplificadas** via classe abstrata `AbstractAndroidNotification`.  
- **Extensões para Context** (rede, duração e tamanho legíveis).  
- **Interface ISimpleListItem** para listas reutilizáveis.  

---

## 🎨 core-android-compose-utils

Integração idiomática do `core-utils` e `core-android-utils` com **Jetpack Compose**.

### Funcionalidades
- Extensões para **Navigation Component** (navegação com resultado).  
- **Gerenciamento de Permissões** em Composables.  
- **Extensões de Mídia** (abrir câmera, player de vídeo, leitor de PDF).  

---

## 📄 android-pdf-generator

Geração de PDFs nativos no Android via `PdfDocument` + `Canvas`.

### Funcionalidades
- Estrutura modular com **Header, Body e Footer**.  
- Gerenciamento de **paginação automática**.  
- Componentes prontos: **TableComponent** e **LayoutGridComponent**.  
- Suporte a preparação de dados, medição e desenho em ciclo de vida claro.  

---

## 📊 compose-charts

Gráficos interativos e animados em **Jetpack Compose**.

### Tipos de Gráficos
- `SimpleBarChart`  
- `GroupedBarChart`  
- `LineChart`  

### Funcionalidades
- Animações integradas.  
- Tooltips interativas.  
- Legendas automáticas.  
- Eixos e linhas de grade customizáveis.  
- Scroll horizontal e tratamento inteligente de rótulos longos.  

---

## 🔥 firebase-toolkit

Facilita a integração com **Firebase**: Authentication, Firestore, Storage, Analytics e Crashlytics.

### Funcionalidades
- Autenticação com email/senha e Google via **Credential Manager**.  
- Extensões para **Analytics** com enums centralizados.  
- Tratamento de erros inteligente com **Crashlytics**.  
- Classe base para **Firestore** com conversão automática para Map.  
- Serviços de **Cloud Storage** com estratégias de download otimizadas.  

---

## ❤️  health-connect-toolkit

Abstração da API **Health Connect**.  
Organizado em **Serviços (records)** e **Mappers (transformação/associação de dados)**.

### Funcionalidades
- Serviços para leitura de registros de saúde.  
- Mappers para conversão em entidades de domínio.  
- Associação automática de registros a entidades existentes (ex: passos ↔ sessões).  
- Preservação de metadados de origem e dispositivos.  

---

## 🎛 ui-compose-components

Coleção de **componentes Compose + Material 3** prontos para uso.  
Baseados em **State + Composable**, promovendo testabilidade e reuso.

### Categorias de Componentes
- **Formulários**: text fields, date/time pickers, dropdowns, seletores.  
- **Botões**: padrões, FAB, icônicos, switches customizados.  
- **Diálogos**: mensagens, listas, paginação.  
- **Listas**: comuns, paginadas, agrupadas, expansíveis.  
- **Layouts**: top/bottom app bars, tabs, grids responsivos.  
- **Outros**: VideoGallery, BottomSheet, indicadores de carregamento.  

---

## ⚙️ work-manager-toolkit

Arquitetura opinativa para **WorkManager**.

### Funcionalidades
- Classes base com tratamento de erros centralizado.  
- Suporte a workers periódicos e one-time.  
- Workers de importação/exportação com autenticação.  
- Interfaces modulares (`ITransactionalWorker`, `IConditionalRunWorker`, `ITokenAuthWorker`).  
- Integração com **DataStore** para controle global de execução.  

---

## ⚡ Instalação

Antes de adicionar as dependências, inclua o repositório do GitHub Packages no seu `settings.gradle.kts` ou `build.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/nikolasluiz123/AndroidLibs")
        }
    }
}
```
### 📑 1. Usando libs.versions.toml
```toml
# versions
coreUtils = "1.0.0"
coreAndroidUtils = "1.0.1"
coreAndroidComposeUtils = "1.0.0"
androidPDFGenerator = "1.0.0"
composeCharts = "1.0.0"
uiComposeComponents = "1.0.4"
firebaseToolKit = "1.0.2"
healthConnectToolKit = "1.0.1"
roomToolKit = "1.0.1"
workManagerToolKit = "1.0.1"

# libraries
androidlibs-core-utils = { group = "br.com.androidlibs", name = "core-utils", version.ref = "coreUtils" }
androidlibs-core-android-utils = { group = "br.com.androidlibs", name = "core-android-utils", version.ref = "coreAndroidUtils" }
androidlibs-core-android-compose-utils = { group = "br.com.androidlibs", name = "core-android-compose-utils", version.ref = "coreAndroidComposeUtils" }
androidlibs-android-pdf-generator = { group = "br.com.androidlibs", name = "android-pdf-generator", version.ref = "androidPDFGenerator" }
androidlibs-compose-charts = { group = "br.com.androidlibs", name = "compose-charts", version.ref = "composeCharts" }
androidlibs-ui-compose-components = { group = "br.com.androidlibs", name = "ui-compose-components", version.ref = "uiComposeComponents" }
androidlibs-firebase-toolkit = { group = "br.com.androidlibs", name = "firebase-toolkit", version.ref = "firebaseToolKit" }
androidlibs-health-connect-toolkit = { group = "br.com.androidlibs", name = "health-connect-toolkit", version.ref = "healthConnectToolKit" }
androidlibs-room-toolkit = { group = "br.com.androidlibs", name = "room-toolkit", version.ref = "roomToolKit" }
androidlibs-work-manager-toolkit = { group = "br.com.androidlibs", name = "work-manager-toolkit", version.ref = "workManagerToolKit" }
```

No seu `build.gradle.kts`:
```gradle
dependencies {
    implementation(libs.androidlibs.core.utils)
    implementation(libs.androidlibs.core.android.utils)
    implementation(libs.androidlibs.core.android.compose.utils)
    implementation(libs.androidlibs.android.pdf.generator)
    implementation(libs.androidlibs.compose.charts)
    implementation(libs.androidlibs.ui.compose.components)
    implementation(libs.androidlibs.firebase.toolkit)
    implementation(libs.androidlibs.health.connect.toolkit)
    implementation(libs.androidlibs.room.toolkit)
    implementation(libs.androidlibs.work.manager.toolkit)
}
```

### 📑 2. Usando o formato tradicional (build.gradle)
```gradle
dependencies {
    implementation("br.com.androidlibs:core-utils:1.0.0")
    implementation("br.com.androidlibs:core-android-utils:1.0.1")
    implementation("br.com.androidlibs:core-android-compose-utils:1.0.0")
    implementation("br.com.androidlibs:android-pdf-generator:1.0.0")
    implementation("br.com.androidlibs:compose-charts:1.0.0")
    implementation("br.com.androidlibs:ui-compose-components:1.0.4")
    implementation("br.com.androidlibs:firebase-toolkit:1.0.2")
    implementation("br.com.androidlibs:health-connect-toolkit:1.0.1")
    implementation("br.com.androidlibs:room-toolkit:1.0.1")
    implementation("br.com.androidlibs:work-manager-toolkit:1.0.1")
}
```
