package com.triton.johnsonapp.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) :

    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(REGISTER_QUERY)
        db?.execSQL(ANIMAL_QUERY)
        db?.execSQL(BREEDINGRECORD_QUERY)
        db?.execSQL(HEALTHINFO_QUERY)
        db?.execSQL(PRODUCT_QUERY)
        db?.execSQL(SEMENTANK_QUERY)
        db?.execSQL(CATEGORY_QUERY)
        db?.execSQL(DISCOUNT_QUERY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        onCreate(db)
    }

    companion object {

        const val DATABASE_NAME = "dairymuneem.db"
        const val DATABASE_VERSION = 1

        const val REGISTER_TABLE = "register_table"
        const val REG_ID = "reg_id"
        const val FNAME = "fname"
        const val LNAME = "lname"
        const val DOB = "dob"
        const val MAIL = "mail"
        const val MOBILE = "mobile"
        const val USER_TYPE = "user_type"
        const val ADDRESSONE = "add_one"
        const val ADDRESSTWO = "add_two"
        const val CITY = "city"
        const val PINCODE = "pincode"
        const val DISTRICT = "district"
        const val STATE = "state"
        const val TEHSIL = "tehsil"
        const val VILLAGE = "village"
        const val GSTNUMBER = "gstnumber"
        const val PANNUMBER = "pannumber"
        const val LICENSE = "license"
        const val LICENSE_NUMBER = "license_number"
        const val EXPIRYDATE = "expirydate"
        const val RSTATUS = "Rstatus"

        const val MYUSER = "user"

        const val ANIMAL_TABLE = "animal_table"
        const val ANIMAL_ID = "animal_id"
        const val ANIMAL_IMAGE = "animal_image"
        const val ANIMAL_TAGID = "tagid"
        const val ANIMAL_TYPE = "animal_type"
        const val ANIMAL_SEX = "animalsex"
        const val ANIMALCOWORCALF = "coworcalf"
        const val ANIMAL_LACTATING = "animallactating"
        const val ANIMAL_DOB = "animaldob"
        const val ANIMAL_BORNOWNFORM = "animalbornownfarm"
        const val ANIMAL_PURCHASEPRICE = "purchaseprice"
        const val ANIMAL_WEIGHT = "animalweight"
        const val ANIMAL_STATUS = "animal_status"

        const val BREEDINGRECORD_TABLE = "breedingrecordtable"
        const val BREED_ID = "breedid"
        const val COWINFO = "cowinfo"
        const val ANIMALDATEOFBIRTH = "animaldob"
        const val ANIMALWEIGHT = "weight"
        const val ANIMLALBREEDING = "breeding"
        const val ANIMALDATEOFAI = "dateofai"
        const val BULLNAME = "bullname"
        const val SEMENCOMPANY = "semencompany"
        const val EXPENSEAI = "expenseai"
        const val BULLMOTHERYIELD = "bullmotheryield"
        const val VETNAME = "vetname"
        const val PREGNENCYSTATUS = "pregnencystatus"
        const val BREEDSTATUS = "breedstatus"

        const val HEALTHINFO_TABLE = "healthinfotable"
        const val HEALTHINFO_ID = "healthinfoid"
        const val UPDATEHEALTHINFO = "healthinfo"
        const val SELECT_ANIMAL = "selectanimal"
        const val DISEASE_NAME = "diseasename"
        const val TREATMENT_DATE = "treatmentdate"
        const val MILKLOSS = "milkloss"
        const val TREATMENT_COST = "treatmentcost"
        const val MEDICINE_DETAILS = "medicinedetails"
        const val HEALTHSTATUS = " healthstatus"

        const val PRODUCT_TABLE = "product_table"
        const val PRODUCT_ID = "product_id"
        const val PRODUCT_NAME = "product_name"
        const val PRODUCT_PRICE = "product_price"
        const val PRODUCT_DISCOUNT = "product_discount"
        const val PRODUCT_QTY = "product_qty"
        const val PRODUCT_DESCRIPTION = "product_description"
        const val PRODUCT_CATEGORY = "product_category"
        const val PRODUCT_STATUS = "status"
        const val PRODUCT_IMAGE = "pro_image"
        const val PRODUCT_MIN = "pro_min"
        const val DELIVERY_CHARGES = "del_charges"
        const val PRODUCTDISCOUNT_STATUS = "productdiscountstatus"

        const val SEMENTANK_TABLE = "sementank_table"
        const val SEMENTANK_ID = "sementank_id"
        const val SEMENTANK_NAME = "sementank_name"
        const val NUMBEROFCANISTERS = "noofcanisters"
        const val SEMENTANK_STATUS = "sementank_status"

        const val CATEGORY_TABLE = "categorytable"
        const val CATEGORY_ID = "category_id"
        const val CATEGORY_NAME = "caterogy_name"
        const val CATEGORY_STATUS = "category_status"

        const val DISCOUNT_TABLE = "discounttable"
        const val DISCOUNT_ID = "discount_id"
        const val DISCOUNT_NAME = "discount_name"
        const val DISCOUNT_VALUE = "discount_value"
        const val DISCOUNT_STATUS = "discount_status"
        const val FROM_DATE = "fromdate"
        const val TO_DATE = "todate"


        const val REGISTER_QUERY = ("CREATE TABLE "
                + REGISTER_TABLE + "( " + REG_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT , " + FNAME
                + " TEXT , " + LNAME
                + " TEXT , " + DOB
                + " TEXT , " + MAIL
                + " TEXT , " + MOBILE
                + " TEXT , " + USER_TYPE
                + " TEXT , " + ADDRESSONE
                + " TEXT , " + ADDRESSTWO
                + " TEXT , " + CITY
                + " TEXT , " + PINCODE
                + " TEXT , " + DISTRICT
                + " TEXT , " + STATE
                + " TEXT , " + TEHSIL
                + " TEXT , " + VILLAGE
                + " TEXT , " + GSTNUMBER
                + " TEXT , " + PANNUMBER
                + " TEXT , " + LICENSE
                + " TEXT , " + LICENSE_NUMBER
                + " TEXT , " + EXPIRYDATE
                + " TEXT , " + RSTATUS
                + " TEXT );"
                )

        const val ANIMAL_QUERY = ("CREATE TABLE "
                + ANIMAL_TABLE + "( " + ANIMAL_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT , " + ANIMAL_TAGID
                + " TEXT , " + ANIMAL_IMAGE
                + " TEXT , " + ANIMAL_TYPE
                + " TEXT , " + ANIMAL_SEX
                + " TEXT , " + ANIMALCOWORCALF
                + " TEXT , " + ANIMAL_LACTATING
                + " TEXT , " + ANIMAL_DOB
                + " TEXT , " + ANIMAL_BORNOWNFORM
                + " TEXT , " + ANIMAL_PURCHASEPRICE
                + " TEXT , " + ANIMAL_WEIGHT
                + " TEXT , " + MYUSER
                + " TEXT , " + ANIMAL_STATUS
                + " TEXT );"
                )

        const val BREEDINGRECORD_QUERY = ("CREATE TABLE "
                + BREEDINGRECORD_TABLE + "( " + BREED_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COWINFO
                + " TEXT , " + ANIMAL_TAGID
                + " TEXT , " + ANIMALDATEOFBIRTH
                + " TEXT , " + ANIMALWEIGHT
                + " TEXT , " + ANIMLALBREEDING
                + " TEXT , " + ANIMALDATEOFAI
                + " TEXT , " + BULLNAME
                + " TEXT , " + SEMENCOMPANY
                + " TEXT , " + EXPENSEAI
                + " TEXT , " + BULLMOTHERYIELD
                + " TEXT , " + VETNAME
                + " TEXT , " + MOBILE
                + " TEXT , " + PREGNENCYSTATUS
                + " TEXT , " + MYUSER
                + " TEXT , " + BREEDSTATUS
                + " TEXT );"
                )

        const val HEALTHINFO_QUERY = ("CREATE TABLE "
                + HEALTHINFO_TABLE + "( " + HEALTHINFO_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT , " + UPDATEHEALTHINFO
                + " TEXT , " + SELECT_ANIMAL
                + " TEXT , " + ANIMAL_TAGID
                + " TEXT , " + DISEASE_NAME
                + " TEXT , " + TREATMENT_DATE
                + " TEXT , " + MILKLOSS
                + " TEXT , " + TREATMENT_COST
                + " TEXT , " + MEDICINE_DETAILS
                + " TEXT , " + MYUSER
                + " TEXT , " + HEALTHSTATUS
                + " TEXT );"
                )



        const val PRODUCT_QUERY = ("CREATE TABLE "
                + PRODUCT_TABLE + "(" + PRODUCT_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME
                + " TEXT , " + PRODUCT_PRICE
                + " TEXT , " + PRODUCT_DISCOUNT
                + " TEXT , " + PRODUCT_QTY
                + " TEXT , " + PRODUCT_DESCRIPTION
                + " TEXT , " + PRODUCT_CATEGORY
                + " TEXT , " + MYUSER
                + " TEXT , " + PRODUCT_STATUS
                + " TEXT , " + PRODUCT_IMAGE
                + " TEXT , " + PRODUCT_MIN
                + " TEXT , " + DELIVERY_CHARGES
                + " TEXT , " + PRODUCTDISCOUNT_STATUS
                + " TEXT );")

        const val SEMENTANK_QUERY = ("CREATE TABLE "
                + SEMENTANK_TABLE + "(" + SEMENTANK_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SEMENTANK_NAME
                + " TEXT , " + NUMBEROFCANISTERS
                + " TEXT , " + SEMENTANK_STATUS
                + " TEXT , " + MYUSER
                + " TEXT );")

        const val CATEGORY_QUERY = ( "CREATE TABLE "
                + CATEGORY_TABLE + "(" + CATEGORY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME
                + " TEXT , " + CATEGORY_STATUS
                + " TEXT , " + MYUSER
                + " TEXT );")



        const val DISCOUNT_QUERY = ( "CREATE TABLE "
                + DISCOUNT_TABLE + "(" + DISCOUNT_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DISCOUNT_NAME
                + " TEXT , " + DISCOUNT_VALUE
                + " TEXT , " + DISCOUNT_STATUS
                + " TEXT , " + FROM_DATE
                + " TEXT , " + TO_DATE
                + " TEXT , " + MYUSER
                + " TEXT );")

    }


}