package com.fiveplus.platform.utils;

import com.fiveplus.platform.model.LessonType;

public class TypeConvertor {
    public LessonType convertType(LessonType type){
            if (type.equals("День Домашки")) {
                return LessonType.HOMEWORK_DAY;
            } else if (type.equals("Началка")) {
                return LessonType.NACHALKA;
            } else {
                return LessonType.OST_MIN;
            }

    }
}
