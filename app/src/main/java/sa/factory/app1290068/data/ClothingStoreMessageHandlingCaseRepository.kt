package sa.factory.app1290068.data

import sa.factory.app1290068.model.ClothingStoreMessageHandlingCase
import sa.factory.app1290068.model.FacebookInboxLeadTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ClothingStoreMessageHandlingCaseRepository {
    fun getAvailableCatalog(): Flow<List<FacebookInboxLeadTask>>
    fun getActiveRequest(): Flow<ClothingStoreMessageHandlingCase?>
    suspend fun submitRequest(request: ClothingStoreMessageHandlingCase): Result<String>
}

class InMemoryClothingStoreMessageHandlingCaseRepository : ClothingStoreMessageHandlingCaseRepository {
    private val _catalog = MutableStateFlow<List<FacebookInboxLeadTask>>(listOf(
        FacebookInboxLeadTask("1", "وصول وتلقي رسائل فيسبوك", "إدارة الرسائل والردود على المتجر عبر الموبايل مع تنظيم المحادثات وتسهيل سرعة الرد اليومي لطلبات حوالي 25 رسالة يومياً.", 5000L, "assignment"),
        FacebookInboxLeadTask("2", "تحويل العميل الجاد إلى واتساب", "قواعد تحويل تلقائي ورسائل جاهزة لإرسال رابط واتساب أو رسالة تمهيدية مع توثيق سبب التحويل لتحسين التحويل ورفع المبيعات.", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<ClothingStoreMessageHandlingCase?>(null)

    override fun getAvailableCatalog(): Flow<List<FacebookInboxLeadTask>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<ClothingStoreMessageHandlingCase?> = _active.asStateFlow()

    override suspend fun submitRequest(request: ClothingStoreMessageHandlingCase): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
