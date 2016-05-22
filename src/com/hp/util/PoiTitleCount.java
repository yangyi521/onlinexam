package com.hp.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PoiTitleCount
{
  public static Iterator<Map.Entry> CountPointTitle(List<String> array)
  {
    Map<String, Integer> map = new HashMap();
    for (String str : array)
    {
      Integer num = (Integer)map.get(str);
      num = Integer.valueOf(num == null ? 1 : num.intValue() + 1);
      map.put(str, num);
    }
    Set set = map.entrySet();
    Object it = set.iterator();
    
    return (Iterator<Map.Entry>)it;
  }
}
