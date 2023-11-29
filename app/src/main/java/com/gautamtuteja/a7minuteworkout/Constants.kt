package com.gautamtuteja.a7minuteworkout

object Constants {


    fun defaultExcesice():ArrayList<ExcerModel>{
        val excerlist = ArrayList<ExcerModel>()

        val jumpingjacks = ExcerModel(1, "Jumping Jacks",R.drawable.ic_jumping_jacks, false,false)
        excerlist.add(jumpingjacks)

        val abdomcrunch = ExcerModel(2,"Abdominal Crunch",R.drawable.ic_abdominal_crunch,false,false)
        excerlist.add(abdomcrunch)

        val highkneerun =ExcerModel(3,"High Knee Spot Running",R.drawable.ic_high_knees_running_in_place,false,false)
        excerlist.add(highkneerun)

        val lunges= ExcerModel(4,"Lunges",R.drawable.ic_lunge,false,false)
        excerlist.add(lunges)

        val plank= ExcerModel(5,"Plank",R.drawable.ic_plank,false,false)
        excerlist.add(plank)

        val pushup= ExcerModel(6,"Push Ups",R.drawable.ic_push_up,false,false)
        excerlist.add(pushup)

        val pushuprotation= ExcerModel(7,"Push Up and Rotation",R.drawable.ic_push_up_and_rotation,false,false)
        excerlist.add(pushuprotation)

        val sideplank= ExcerModel(8,"Side Plank",R.drawable.ic_side_plank,false,false)
        excerlist.add(sideplank)

        val squat =  ExcerModel(9,"Squat",R.drawable.ic_squat,false,false)
        excerlist.add(squat)

        val steupchair= ExcerModel(10,"Step Up on Chair",R.drawable.ic_step_up_onto_chair,false,false)
        excerlist.add(steupchair)

        val wallsit= ExcerModel(11,"Wall Sit",R.drawable.ic_wall_sit,false,false)
        excerlist.add(wallsit)

        val tricepdips= ExcerModel(12,"Triceps Dip on Chair",R.drawable.ic_triceps_dip_on_chair,false,false)
        excerlist.add(tricepdips)



        return excerlist
    }
}