package com.shopping.aop;

import com.shopping.constant.SysConstant;
import com.shopping.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * controller层通知
 * Created by pq on 2017/7/27.
 */
@Component
public class ControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
    private static final Integer controller_method_warn_threshold=5000;
    @Autowired
    private HttpServletRequest request;
    /**
     * 在核心业务执行前执行，不能阻止核心业务的调用。
     * @param joinPoint
     */
    private void doBefore(JoinPoint joinPoint) {

    }

    /**
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理
     *
     * 注意：当核心业务抛异常后，立即退出，转向After Advice
     * 执行完毕After Advice，再转到Throwing Advice
     * @param pjp
     * @return
     * @throws Throwable
     */
    private Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        //调用核心逻辑
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();
        logger.info("调用 {}.{} 方法成功！耗时:{} 毫秒", pjp.getTarget().getClass().getName(),
                pjp.getSignature().getName(), end-start);
        if ((end-start) > controller_method_warn_threshold) {
            logger.warn("********************************警告********************************");
            logger.warn("{}.{} 方法耗时 {} 毫秒，超过阈值({})，考虑优化！",
                    pjp.getTarget().getClass().getName(), pjp.getSignature().getName(),
                    end-start, controller_method_warn_threshold);
            logger.warn("********************************警告********************************");
        }
        return retVal;
    }

    /**
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     * @param joinPoint
     */
    private void doAfter(JoinPoint joinPoint) {

    }

    /**
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     * @param joinPoint
     */
    private void doReturn(JoinPoint joinPoint) {

    }

    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     * @param joinPoint
     * @param ex
     */
    private void doThrowing(JoinPoint joinPoint, Throwable ex) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        Long user_id = (user == null ? null : Long.valueOf(user.getUser_id()));
        logger.error("调用 {}.{} 方法时出错！原因:{}",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(), ex.getMessage());
    }

    private Long getUserId() {
        try {
            User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER_INFO);
            if (user == null) {
                return null;
            }
            return Long.valueOf(user.getUser_id());
        } catch (Exception e) {
            return null;
        }
    }
}
