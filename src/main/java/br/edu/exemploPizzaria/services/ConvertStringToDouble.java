//package br.edu.exemploPizzaria.services;
//
//import org.bson.json.StrictJsonWriter;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ConvertStringToDouble implements Converter<String, Double> {
//
//
//    @Override
//    public Double convert(String source) {
//        source=source.trim(); //remove espaços em branco
//        if(source.length()>0){
//            source = source.replace(".",",").replace("", ",");
//            return Double.parseDouble(source);
//
//        }
//        return 0.;
//    }
//}
