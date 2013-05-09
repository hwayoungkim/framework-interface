package com.example.framework.core.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * DumpUtil
 *
 * @date 2012. 3. 21. 오후 5:58:13
 * @version $Id: DumpUtil.java 15060 2013-04-12 06:57:10Z xenomity $
 */
@SuppressWarnings({"unchecked","rawtypes"})
public final class DumpUtil {

	
	private static final String NEW_LINE = "\n";
	private static final String TAB = "\t";

	private static final String PARENTHESES_COLON_EMPTY = "(): ";
	
	private static final String COMMA= ",";
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSE_BRACKET = "]";
	private static final String CLOSE_BRACKET_COLON_EMPTY = "]: ";
	
	
	
	private DumpUtil() {
		
	}
    public static String toDumpString2(final Object object) {
        return ToStringBuilder.reflectionToString(object, ToStringStyle.MULTI_LINE_STYLE);
    }


	private static List convertSetToList(final Set set) {
        Object[] array = set.toArray();
        List list = new ArrayList();
        for (Object o : array) {
            list.add(o);
        }
        return list;
    }

	private static void putSubMap( StringBuffer sb, Map map) {
         List keyList = convertSetToList(map.keySet());
         Collections.sort(keyList);

         for (Object oKey : keyList) {

             String key = (String) oKey;
             Object o2 = map.get(key);

             StringBuffer sbMapValue = new StringBuffer();

             //List?
             if (o2 instanceof List) {
                 List valueList = (List) o2;
                 for (int i = 0; i < valueList.size(); i++) {
                     sbMapValue.append(valueList.get(i)).append(COMMA);
                 }
                 if (sbMapValue.length() > 0) {
                 	sbMapValue.setLength(sbMapValue.length() - 1);
                 }
             }
             //String[]
             else if (o2 instanceof String[]) {
                 String[] valueArray = (String[]) o2;
                 for (int i = 0; i < valueArray.length; i++) {
                     sbMapValue.append(valueArray[i]).append(COMMA);
                 }
                 if (sbMapValue.length() > 0) {
                 	sbMapValue.setLength(sbMapValue.length() - 1);
                 }
             } 
             // byte[] 배열인 경우 안찍음.
             else if (o2 instanceof byte[]) {
                 sbMapValue.append("byte[]'s length: ").append(((byte[])o2).length);
                 
             } 
             
             else {
                 sbMapValue.append(o2);
             }

             sb.append(TAB).append(OPEN_BRACKET).append(key).append(CLOSE_BRACKET_COLON_EMPTY).append(sbMapValue.toString()).append(NEW_LINE);
         }
         sb.append(CLOSE_BRACKET).append(NEW_LINE);
		
	}
	private static void putSubList(StringBuffer sb, List list) {
        for (int j = 0; j < list.size(); j++) {
            Object childObject = list.get(j);
            sb.append("\t [").append( j).append(CLOSE_BRACKET_COLON_EMPTY).append(childObject).append(NEW_LINE);
        }
        sb.append(CLOSE_BRACKET).append(NEW_LINE);
	}
	
	private static void putSubStringArray(StringBuffer sb, String[] strs) {
          for (int j = 0; j < strs.length; j++) {
              Object childObject = strs[j];
              sb.append(TAB).append(OPEN_BRACKET).append( j).append(CLOSE_BRACKET_COLON_EMPTY).append(childObject).append(NEW_LINE);
          }
          sb.append(CLOSE_BRACKET).append(NEW_LINE);
	}
	
	
	private static boolean isSkipMethodInfo(Method method) {
		
		 String methodName = method.getName();
		 
		 
		 if ( method.getParameterTypes().length != 0) {
			 return true;
		 }
		 if (methodName.startsWith("is")) {
			 return false;
		 }
		 if (methodName.startsWith("get")) {
			 return false;
		 }
         return true;
	}
	
	private static void putSubClass(StringBuffer sb, Object object) {
		Method[] methods = object.getClass().getMethods();

        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();

            if(isSkipMethodInfo(methods[i])) {
            	continue;
            }
            
            Object ret1 = null;
            String errorMessage = "";
                try {
					ret1 = methods[i].invoke(object);
				} catch (Exception e) {
					errorMessage = e.getLocalizedMessage();
	                sb.append(TAB).append( methodName).append(PARENTHESES_COLON_EMPTY).append("_________ERROR! ").append(errorMessage).append(NEW_LINE);
	                continue;
				}

            
            if (ret1 instanceof List) {
                List list = (List) ret1;

                for (int j = 0; j < list.size(); j++) {
                    Object childObject = list.get(j);
                    sb.append(TAB).append(methodName).append(PARENTHESES_COLON_EMPTY).append(list).append(OPEN_BRACKET).append( j)
                    .append(CLOSE_BRACKET_COLON_EMPTY).append(childObject ).append(NEW_LINE);
                }

            } else if (ret1 instanceof Map) {
                Map map = (Map) ret1;
                Iterator iterator = map.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = "" + iterator.next();
                    String mapValue1 = "" + map.get(key);
                    sb.append(TAB).append( methodName).append(PARENTHESES_COLON_EMPTY).append(map).append( OPEN_BRACKET)
                    .append( key).append(CLOSE_BRACKET_COLON_EMPTY)
                    .append(mapValue1).append(NEW_LINE);
                }
            // byte[] 배열인 경우 안찍음.
            } else if (ret1 instanceof byte[]) {
                sb.append(TAB).append(methodName).append(PARENTHESES_COLON_EMPTY + "byte[]'s length: "+ ((byte[])ret1).length+NEW_LINE);
                //커스터 마이징 
            }  else {
                sb.append(TAB).append(methodName).append(PARENTHESES_COLON_EMPTY ).append(ret1).append(NEW_LINE);
            }
        }
        sb.append(CLOSE_BRACKET).append(NEW_LINE);
	}
	
    public static String toDumpString(final Object object) {
        StringBuffer sb = new StringBuffer();

        if (object == null) {
            return sb.append("null").toString();
        }

        sb.append(NEW_LINE ).append(object.getClass().getName()).append(OPEN_BRACKET).append(NEW_LINE);

        if (object instanceof Map) {
        	putSubMap(sb,(Map)object);
        }
        else if (object instanceof List) {
        	putSubList(sb,(List) object);
        }
        else if (object instanceof String[]) {
        	putSubStringArray(sb,(String[]) object);
        } else {
        	putSubClass(sb,object);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        
    }
}
