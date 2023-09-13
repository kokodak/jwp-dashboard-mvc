package webmvc.org.springframework.web.servlet.mvc.tobe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import webmvc.org.springframework.web.servlet.ModelAndView;

public class HandlerExecution {

    private final Object handler;
    private final Method handlerMethod;

    public HandlerExecution(final Object handler,
                            final Method handlerMethod) {
        validateDeclare(handler, handlerMethod);
        validateHandlerMethod(handlerMethod);
        this.handler = handler;
        this.handlerMethod = handlerMethod;
    }

    private void validateDeclare(final Object handler,
                                 final Method handlerMethod) {
        final Class<?> handlerClass = handler.getClass();
        final Class<?> declaringClass = handlerMethod.getDeclaringClass();
        if (!handlerClass.equals(declaringClass)) {
            throw new IllegalArgumentException("Method not found in Handler");
        }
    }

    private void validateHandlerMethod(final Method handlerMethod) {
        final List<Class<?>> classes = Arrays.asList(handlerMethod.getParameterTypes());
        if (!(classes.contains(HttpServletRequest.class) && classes.contains(HttpServletResponse.class))) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
    }

    public ModelAndView handle(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return (ModelAndView) handlerMethod.invoke(handler, request, response);
    }
}
