package com.example.site.common.resolver;

import com.example.site.common.common.CommandMap;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CommandMap.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        CommandMap commandMap = new CommandMap();
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Enumeration<?> enumeration = request.getParameterNames();

        String key;
        String value;
        String[] values;

        while(enumeration.hasMoreElements()){

            key = (String) enumeration.nextElement();
            values = request.getParameterValues(key);

            if(values != null){
                commandMap.put(key,(values.length > 1) ? values : values[0]);
            }

        }

        Map<?, ?> pathVariableMap = (Map<? , ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        for (Object o : pathVariableMap.keySet()) {
            key = (String) o;
            value = (String) pathVariableMap.get(key);
            commandMap.put(key, value);
        }

        return commandMap;
    }
}
