package sa.factory.app1290068.model

import kotlinx.serialization.Serializable

@Serializable
data class FacebookInboxLeadTask(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class ClothingStoreMessageHandlingCase(
    val requestId: String,
    val items: List<FacebookInboxLeadTask>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: ClothingStoreMessageHandlingCaseStatus = ClothingStoreMessageHandlingCaseStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class ClothingStoreMessageHandlingCaseStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
