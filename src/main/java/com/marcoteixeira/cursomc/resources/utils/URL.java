package com.marcoteixeira.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String s){
        try {
           return URLDecoder.decode(s, "UTF-8");
        }catch (UnsupportedEncodingException e){
           return "";
        }
    }

    //Converter string em lista de Integer
    public static List<Integer> decodeIntList(String s) {
        String[] vet = s.split(",");
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < vet.length; i++) {
            lista.add(Integer.parseInt(vet[i]));
        }
        return lista;
    }

    //Usando lambda para converter string em lista de Integer
    public static List<Integer> decodeIntListLambda(String s) {
        return Arrays.asList(s.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
