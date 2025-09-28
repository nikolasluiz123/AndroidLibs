package br.com.core.android.utils.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Uma classe base abstrata para criar e exibir notificações no Android.
 *
 * Esta classe simplifica o processo de criação de notificações, gerenciando a criação
 * do [NotificationChannel] (necessário para Android 8.0 Oreo e superior) e a construção
 * da notificação básica.
 *
 * Para usar, crie uma subclasse e implemente os métodos abstratos para definir as
 * propriedades do canal de notificação e o ícone. Você também pode sobrescrever os
 * métodos `open` para customizar ainda mais o comportamento da notificação.
 *
 * ### Exemplo de Uso:
 * ```kotlin
 * class MySimpleNotification(context: Context) : AbstractAndroidNotification(context) {
 * override fun getChannelId(): String = "my_channel_id"
 * override fun getChannelName(): String = "Notificações Gerais"
 * override fun getChannelDescription(): String = "Canal para notificações gerais do app."
 * override fun getImportance(): Int = NotificationManager.IMPORTANCE_DEFAULT
 * override fun getSmallIcon(): Int = R.drawable.ic_notification
 *
 * // Opcional: Adicionar ações, intents, etc.
 * override fun onBuildNotification(builder: NotificationCompat.Builder) {
 * val intent = Intent(context, MainActivity::class.java)
 * val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
 * builder.setContentIntent(pendingIntent)
 * }
 * }
 *
 * // Para exibir a notificação:
 * MySimpleNotification(context).showNotification(title = "Olá", message = "Esta é uma notificação de teste.")
 * ```
 *
 * @property context O [Context] da aplicação, usado para acessar serviços do sistema como o [NotificationManager].
 */
abstract class AbstractAndroidNotification(protected val context: Context) {

    /**
     * Define o ID único para o canal de notificação.
     * Este ID é usado internamente pelo sistema Android. Recomenda-se usar um valor constante.
     * @return A [String] de identificação do canal.
     */
    protected abstract fun getChannelId(): String

    /**
     * Define o nome do canal de notificação que será visível para o usuário nas configurações do sistema.
     * @return O nome legível do canal.
     */
    protected abstract fun getChannelName(): String

    /**
     * Define a descrição do canal de notificação, também visível para o usuário nas configurações.
     * @return A descrição do propósito do canal.
     */
    protected abstract fun getChannelDescription(): String

    /**
     * Define a importância do canal de notificação (ex: [NotificationManager.IMPORTANCE_DEFAULT],
     * [NotificationManager.IMPORTANCE_HIGH], etc.).
     * @return O nível de importância (`Int`).
     */
    protected abstract fun getImportance(): Int

    /**
     * Define o ícone pequeno que aparecerá na barra de status.
     * @return O ID do recurso drawable (`R.drawable.ic_...`).
     */
    protected abstract fun getSmallIcon(): Int

    /**
     * Ponto de extensão para customizar a notificação antes de ser construída.
     * Use este método para adicionar ações, intents, estilos, etc., ao [NotificationCompat.Builder].
     *
     * @param builder O [NotificationCompat.Builder] da notificação que está sendo construída.
     */
    protected open fun onBuildNotification(builder: NotificationCompat.Builder) = Unit

    /**
     * Define o ID da notificação. Notificações com o mesmo ID irão se sobrepor.
     * O padrão é usar o timestamp atual para garantir que cada notificação seja única.
     * @return O ID (`Int`) para esta notificação.
     */
    protected open fun getNotificationId(): Int = System.currentTimeMillis().toInt()

    /**
     * Define se a notificação deve ser cancelada automaticamente quando o usuário tocar nela.
     * O padrão é `true`.
     * @return `true` para auto-cancelar, `false` caso contrário.
     */
    protected open fun getAutoCancel(): Boolean = true

    /**
     * Cria o canal de notificação (se necessário) e exibe a notificação na central de notificações do dispositivo.
     *
     * Requer a permissão `android.permission.POST_NOTIFICATIONS` no Android 13 (API 33) e superior.
     *
     * @param title O título da notificação.
     * @param message O corpo da mensagem da notificação.
     */
    @SuppressLint("MissingPermission")
    fun showNotification(title: String = "", message: String = "") {
        createNotificationChannel()

        val defaultNotificationBuilder = NotificationCompat.Builder(context, getChannelId())
            .setSmallIcon(getSmallIcon())
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(getAutoCancel())

        onBuildNotification(defaultNotificationBuilder)

        val notification = defaultNotificationBuilder.build()

        with(NotificationManagerCompat.from(context)) {
            notify(getNotificationId(), notification)
        }
    }

    /**
     * Cria o [NotificationChannel] necessário para exibir notificações no Android 8.0 (API 26) e superior.
     * Se o canal já existir, nenhuma operação é realizada.
     */
    private fun createNotificationChannel() {
        val channel = NotificationChannel(getChannelId(), getChannelName(), getImportance()).apply {
            description = getChannelDescription()
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}