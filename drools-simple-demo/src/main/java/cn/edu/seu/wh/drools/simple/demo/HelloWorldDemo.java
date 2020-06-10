package cn.edu.seu.wh.drools.simple.demo;

import cn.edu.seu.wh.drools.simple.demo.entity.Message;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class HelloWorldDemo {
    public static void main(String[] args) {
        // KieServices is the factory for all KIE services
        KieServices kieServices = KieServices.Factory.get();
        // KieContainer是重量级组件，建议复用
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        execute(kieContainer);
    }


    /**
    * @Description: 具体执行逻辑
    * @Param: [kieContainer]
    * @return: void
    * @Author: Wang Huan(https://github.com/njustwh2014)
    * @Date: 2020/6/5
    */
    private static void execute(KieContainer kieContainer) {
        // 依赖KieContainer产生一个KieSession，它的定义和配置在 META-INF/kmodule.xml
        KieSession kieSession=kieContainer.newKieSession("HelloWorldKS");

        // 一旦KieSession生成，application可以和它交互
        // 这个demo里声明了一个global变量list 在rules/HelloWorld.drl文件里
        List<Object> list=new ArrayList<Object>();
        kieSession.setGlobal("list",list);

        // application也可以设置监听器
        kieSession.addEventListener(new DebugAgendaEventListener());
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());

        // To setup a file based audit logger, uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );

        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );


        // application插入facts into session
        final Message message=new Message();
        message.setMessage("Hello World!");
        message.setStatus(Message.HELLO);
        kieSession.insert(message);

        // 执行所有rules
        kieSession.fireAllRules();

        for(Object obj:list){
            System.out.println("Object in list:"+obj.toString());
        }


        // Remove comment if using logging
        // logger.close();

        kieSession.dispose();

    }
}
