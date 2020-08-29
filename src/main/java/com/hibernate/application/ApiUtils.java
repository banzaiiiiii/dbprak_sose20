package com.hibernate.application;

import java.util.List;


public class ApiUtils
{
    public static <T> List<T> getIntersection(List<T> list1, List<T> list2)
    {
        list1.retainAll(list2);
        return list1;
    }
}
