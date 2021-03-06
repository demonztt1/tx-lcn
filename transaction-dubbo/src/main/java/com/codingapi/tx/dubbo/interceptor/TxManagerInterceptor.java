package com.codingapi.tx.dubbo.interceptor;


import com.alibaba.dubbo.rpc.RpcContext;
//import com.lorne.tx.bean.TxTransactionCompensate;
import com.codingapi.tx.aop.service.AspectBeforeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lorne on 2017/6/7.
 */

@Component
public class TxManagerInterceptor {


    @Autowired
    private AspectBeforeService aspectBeforeService;


    public Object around(ProceedingJoinPoint point) throws Throwable {

        String groupId = null;
        int maxTimeOut = 0;
        try {
            groupId = RpcContext.getContext().getAttachment("tx-group");
            maxTimeOut = Integer.parseInt(RpcContext.getContext().getAttachment("tx-maxTimeOut"));
        }catch (Exception e){}
        return aspectBeforeService.around(groupId,maxTimeOut,point);
    }

}
