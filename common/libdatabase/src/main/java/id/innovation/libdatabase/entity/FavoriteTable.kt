package id.innovation.libdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.innovation.libdatabase.entity.MerchantTable.Companion.TABLE_NAME
import id.innovation.libsecurity.SecurityGuards

@Entity(tableName = TABLE_NAME)
data class MerchantTable(
        @PrimaryKey
        @ColumnInfo(name = Column.MERCHANT_PHONE_NUMBER)
        val merchantPhoneNumber: String = "",

        @ColumnInfo(name = Column.ID)
        val id: String? = null,

        @ColumnInfo(name = Column.OWNER_NAME)
        val ownerName: String = "",

        @ColumnInfo(name = Column.OWNER_ADDRESS)
        val ownerAddress: String = "",

        @ColumnInfo(name = Column.OWNER_TAX_NUMBER)
        val ownerTaxNumber: String = "",

        @ColumnInfo(name = Column.MERCHANT_NAME)
        val merchantName: String = "",

        @ColumnInfo(name = Column.MERCHANT_CATEGORY)
        val merchantCategory: String = "",

        @ColumnInfo(name = Column.MERCHANT_OPERATIONAL_HOUR)
        val merchantOperationHour: String = "",

        @ColumnInfo(name = Column.MERCHANT_LATITUDE)
        val merchantLatitude: String = "",

        @ColumnInfo(name = Column.MERCHANT_LONGITUDE)
        val merchantLongitude: String = "",

        @ColumnInfo(name = Column.MERCHANT_ADDRESS_FEATURE)
        val merchantAddressFeature: String = "",

        @ColumnInfo(name = Column.MERCHANT_ADDRESS)
        val merchantAddress: String = "",

        @ColumnInfo(name = Column.MERCHANT_PROVINCE)
        val merchantProvince: String = "",

        @ColumnInfo(name = Column.MERCHANT_CITY)
        val merchantCity: String = "",

        @ColumnInfo(name = Column.MERCHANT_DISTRICT)
        val merchantDistrict: String = "",

        @ColumnInfo(name = Column.MERCHANT_REGENCY)
        val merchantRegency: String = "",

        @ColumnInfo(name = Column.MERCHANT_POSTAL_CODE)
        val merchantPostalCode: String = "",

        @ColumnInfo(name = Column.MERCHANT_GOOGLE_PLACE_ID)
        val merchantGooglePlaceId: String = "",

        @ColumnInfo(name = Column.MERCHANT_PRESENCE_TYPE)
        val merchantPresenceType: String = "",

        @ColumnInfo(name = Column.MERCHANT_SOCMED_PLATFORM)
        val merchantSocmedPlatform: String = "",

        @ColumnInfo(name = Column.MERCHANT_SOCMED_USERNAME)
        val merchantSocmedUsername: String = ""
) {

    companion object {
        const val TABLE_NAME = "merchant"

        object Column {
            const val MERCHANT_PHONE_NUMBER = "merchant_phone_number"
            const val ID = "id"
            const val OWNER_NAME = "name"
            const val OWNER_ADDRESS = "home_address"
            const val OWNER_TAX_NUMBER = "number_npwp"
            const val MERCHANT_NAME = "merchant_name"
            const val MERCHANT_CATEGORY = "merchant_category"
            const val MERCHANT_OPERATIONAL_HOUR = "merchant_operational_hours"
            const val MERCHANT_LATITUDE = "merchant_latitude"
            const val MERCHANT_LONGITUDE = "merchant_langitude"
            const val MERCHANT_ADDRESS_FEATURE = "merchant_address_feature"
            const val MERCHANT_ADDRESS = "merchant_address"
            const val MERCHANT_CITY = "merchant_city"
            const val MERCHANT_DISTRICT = "merchant_district"
            const val MERCHANT_REGENCY = "merchant_regency"
            const val MERCHANT_POSTAL_CODE = "merchant_postal_code"
            const val MERCHANT_PRESENCE_TYPE = "merchant_presence_type"
            const val MERCHANT_SOCMED_PLATFORM = "merchant_socmed_platform"
            const val MERCHANT_SOCMED_USERNAME = "merchant_socmed_username"
            const val MERCHANT_PROVINCE = "merchant_province"
            const val MERCHANT_GOOGLE_PLACE_ID = "merchant_google_place_id"
        }
    }
}

fun MerchantTable.decrypt(securityGuards: SecurityGuards): MerchantTable = with(securityGuards, {
    MerchantTable(
            merchantPhoneNumber = getStringDecrypted(merchantPhoneNumber),
            id = id,
            ownerName = getStringDecrypted(ownerName),
            ownerAddress = getStringDecrypted(ownerAddress),
            ownerTaxNumber = getStringDecrypted(ownerTaxNumber),
            merchantName = merchantName,
            merchantCategory = merchantCategory,
            merchantOperationHour = merchantOperationHour,
            merchantLatitude = getStringDecrypted(merchantLatitude),
            merchantLongitude = getStringDecrypted(merchantLongitude),
            merchantAddress = getStringDecrypted(merchantAddress),
            merchantCity = getStringDecrypted(merchantCity),
            merchantDistrict = getStringDecrypted(merchantDistrict),
            merchantRegency = getStringDecrypted(merchantRegency),
            merchantPostalCode = getStringDecrypted(merchantPostalCode),
            merchantPresenceType = merchantPresenceType,
            merchantSocmedPlatform = merchantSocmedPlatform,
            merchantSocmedUsername = getStringDecrypted(merchantSocmedUsername)
    )
})

fun MerchantTable.encrypt(securityGuards: SecurityGuards) = with(securityGuards, {
    MerchantTable(
            merchantPhoneNumber = encryptedValue(merchantPhoneNumber),
            id = id,
            ownerName = encryptedValue(ownerName),
            ownerAddress = encryptedValue(ownerAddress),
            ownerTaxNumber = encryptedValue(ownerTaxNumber),
            merchantName = merchantName,
            merchantCategory = merchantCategory,
            merchantOperationHour = merchantOperationHour,
            merchantLatitude = encryptedValue(merchantLatitude),
            merchantLongitude = encryptedValue(merchantLongitude),
            merchantAddress = encryptedValue(merchantAddress),
            merchantCity = encryptedValue(merchantCity),
            merchantDistrict = encryptedValue(merchantDistrict),
            merchantRegency = encryptedValue(merchantRegency),
            merchantPostalCode = encryptedValue(merchantPostalCode),
            merchantPresenceType = merchantPresenceType,
            merchantSocmedPlatform = merchantSocmedPlatform,
            merchantSocmedUsername = encryptedValue(merchantSocmedUsername)
    )
})
