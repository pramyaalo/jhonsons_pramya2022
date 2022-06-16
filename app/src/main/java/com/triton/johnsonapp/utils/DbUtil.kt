package com.triton.johnsonapp.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbUtil (var context: Context) {

    var values : ContentValues
    var dbHelper : DBHelper? = null

    fun open() : SQLiteDatabase? {
        if (DBHelper == null) {
            DBHelper = DBHelper(context)
        }
        db = DBHelper!!.writableDatabase
        return db
    }

    fun close() {
        if(DBHelper != null) {
            db!!.close()
        }
    }

    fun addRegUser(
        strFname: String,
        strLname: String,
        strDob: String,
        strMail: String,
        strMobile: String,
        strAddressone: String,
        strAddresstwo: String,
        strCity: String,
        strPincode: String,
        strDistrict: String,
        strState: String,
        str_User: String
    ): Long {
        values.clear()
        values.put(DBHelper.FNAME,strFname)
        values.put(DBHelper.LNAME,strLname)
        values.put(DBHelper.DOB,strDob)
        values.put(DBHelper.MAIL,strMail)
        values.put(DBHelper.MOBILE,strMobile)
        values.put(DBHelper.USER_TYPE,str_User)
        values.put(DBHelper.ADDRESSONE,strAddressone)
        values.put(DBHelper.ADDRESSTWO,strAddresstwo)
        values.put(DBHelper.CITY,strCity)
        values.put(DBHelper.PINCODE,strPincode)
        values.put(DBHelper.DISTRICT,strDistrict)
        values.put(DBHelper.STATE,strState)
        return db!!.insert(DBHelper.REGISTER_TABLE,null,values)
    }

    fun getRegisterbyMobile(strMobile: String): Cursor {
        return db!!.query(
            DBHelper.REGISTER_TABLE,
            REGISTER_FIELD,
            DBHelper.MOBILE + "= '" + strMobile + "'", null,null,null,null
        )
    }

    fun addRegVendor(
        strName: String,
        strMail: String,
        strMobile: String,
        strPincode: String,
        strState: String,
        strDistrict: String,
        strTehsil: String,
        strVillage: String,
        strAddress: String,
        strGstnumber: String,
        strPannumber: String,
        strLicensenumber: String,
        strExpirydate: String,
        str_License: String,
        str_User: String
    ): Long {
        values.clear()
        values.put(DBHelper.FNAME,strName)
        values.put(DBHelper.MAIL,strMail)
        values.put(DBHelper.MOBILE,strMobile)
        values.put(DBHelper.PINCODE,strPincode)
        values.put(DBHelper.STATE,strState)
        values.put(DBHelper.DISTRICT,strDistrict)
        values.put(DBHelper.TEHSIL,strTehsil)
        values.put(DBHelper.VILLAGE,strVillage)
        values.put(DBHelper.ADDRESSONE,strAddress)
        values.put(DBHelper.GSTNUMBER,strGstnumber)
        values.put(DBHelper.PANNUMBER,strPannumber)
        values.put(DBHelper.LICENSE,str_License)
        values.put(DBHelper.LICENSE_NUMBER,strLicensenumber)
        values.put(DBHelper.EXPIRYDATE,strExpirydate)
        values.put(DBHelper.USER_TYPE,str_User)
        return db!!.insert(DBHelper.REGISTER_TABLE,null,values)
    }

    fun adddoctor(strMobile: String, strUser: String): Long {
        values.clear()
        values.put(DBHelper.MOBILE,strMobile)
        values.put(DBHelper.USER_TYPE,strUser)
        return db!!.insert(DBHelper.REGISTER_TABLE,null,values)
    }

    fun addAnimal(
        strTagid: String,
        strLactating: String,
        strDob: String,
        strPurchaseprice: String,
        strAnimalnametype: String,
        strWeight: String,
        strImage: String,
        strGetmobile: String,
        str_Sex: String
    ): Long {

        values.clear()
        values.put(DBHelper.ANIMAL_TAGID,strTagid)
        values.put(DBHelper.ANIMAL_LACTATING,strLactating)
        values.put(DBHelper.ANIMAL_DOB,strDob)
        values.put(DBHelper.ANIMAL_PURCHASEPRICE,strPurchaseprice)
        values.put(DBHelper.ANIMAL_TYPE,strAnimalnametype)
        values.put(DBHelper.ANIMAL_WEIGHT,strWeight)
        values.put(DBHelper.ANIMAL_IMAGE,strImage)
        values.put(DBHelper.MYUSER,strGetmobile)
        values.put(DBHelper.ANIMAL_SEX,str_Sex)
        return db!!.insert(DBHelper.ANIMAL_TABLE,null,values)
    }



    fun getAnimal(str_getmobile: String): Cursor {
        return db!!.query(
            DBHelper.ANIMAL_TABLE,
            ANIMAL_FIELD,
            DBHelper.MYUSER + "= '" + str_getmobile + "'",
            null,null,null,null
        )
    }

    fun getAnimalbytagid(tagid: String): Cursor {
        return db!!.query(
            DBHelper.ANIMAL_TABLE,
            ANIMAL_FIELD,
            DBHelper.ANIMAL_TAGID + "= '" + tagid + "'",
            null,null,null,null
        )
    }
