package com.car.repository;

import com.car.model.Car;

public class GenerateQuery {

    public static String generateWhereClause(Car car){
        StringBuilder builder = new StringBuilder("select * from car where");
        if((car.getName()!= null) && !(car.getName().isEmpty())){
            builder.append(" name = '").append(car.getName()).append("' and");
        }if ((car.getManufactureName() != null ) && !(car.getManufactureName().isEmpty())){
            builder.append(" manufactureName = '").append(car.getManufactureName()).append("' and");
        }if((car.getModel() != null) && !(car.getModel().isEmpty())){
            builder.append(" model = '").append(car.getModel()).append("' and");
        }if ((car.getManufactureYear() != null) && !(car.getManufactureYear().isEmpty())){
            builder.append(" manufactureYear = '").append(car.getManufactureYear()).append("' and");
        }if((car.getColor() != null) && !car.getColor().isEmpty()){
            builder.append(" color ='").append(car.getColor()).append("'");
        }
        return andCondition(builder.toString());
    }

    public static String andCondition(String s){
        int l = s.length();
        String and = s.substring(l-3, l);
        if(and.equals("and")){
            return s.substring(0, l-3);
        }
        return s;
    }
}
