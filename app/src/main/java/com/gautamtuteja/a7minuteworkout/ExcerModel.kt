package com.gautamtuteja.a7minuteworkout

class ExcerModel (
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isComplete: Boolean,
    private var isSelect: Boolean ){

    fun getId():Int{
        return id
    }
    fun setId(id: Int){
        this.id = id
    }

    fun getName():String{
        return name
    }
    fun setName(name: String){
        this.name = name
    }

    fun getImage():Int{
        return image
    }
    fun setImage(id: Int){
        this.image = image
    }

    fun getComplete():Boolean{
        return isComplete
    }
    fun setComplete(id: Boolean){
        this.isComplete = id
    }

    fun getSlected():Boolean{
        return isSelect
    }
    fun setSelected(id: Boolean){
        this.isSelect = id
    }

}