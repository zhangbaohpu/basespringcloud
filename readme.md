# 一.Eureka
>Eureka是Netflix开源的基于REST的服务治理方案，Spring Cloud集成了Eureka，提供服务治理和服务发现功能，可以和基于Spring Boot搭建的微服务应用轻松完成整合。

Spring Cloud Eureka的组成:
- Eureka Server，注册中心
- Eureka Client，所有要进行注册的微服务通过Eureka Client连接到Eureka Server，完成注册
# 二.RestTemplate
>RestTemplate 是 Spring 框架提供的基于 REST 的服务组件，底层是对 HTTP 请求及响应进⾏了封装， 提供了很多访问 RETS 服务的⽅法，可以简化代码开发。

```import com.zhangbao.entity.Student;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   import org.springframework.web.client.RestTemplate;
   import java.util.List;
   /**
    * @author zhangbao
    * @date 2021/3/14 15:31
    **/
   @RestController
   @RequestMapping("/rest")
   public class StudentController {
       @Autowired
       private RestTemplate restTemplate;

       @GetMapping("/findAll")
       public List<Student> findAll(){
           return restTemplate.getForEntity("http://localhost:8010/student/findAll",List.class).getBody();
       }
       @GetMapping("/findAll2")
       public List<Student> findAll2(){
           return restTemplate.getForObject("http://localhost:8010/student/findAll",List.class);
       }
   
       @GetMapping("/findById/{id}")
       public Student findById(@PathVariable("id") Long id){
           return restTemplate.getForEntity("http://localhost:8010/student/findById/{id}",Student.class,id).getBody();
       }
   }
```
# 三.Zuul
>Zuul 是 Netflix 提供的⼀个开源的 API ⽹关服务器，是客户端和⽹站后端所有请求的中间层，对外开放 ⼀个 API，将所有请求导⼊统⼀的⼊⼝，屏蔽了服务端的具体实现逻辑，Zuul 可以实现反向代理的功 能，在⽹关内部实现动态路由、身份认证、IP 过滤、数据监控等。Zuul也是Spring Cloud集成的组件，通过它来实现服务网关。

Zuul的功能列表：
1. 身份认证与安全：识别每个资源的验证要求，并拒绝那些与要求不符合的请求。

2. 审查与监控：在边缘位置追踪有意义的数据和统计结果，从而带来精确的生产视图。

3. 动态路由：动态的将请求路由到不同的后端集群。

4. 压力测试：逐渐增加指向集群的流量，以了解性能。

5. 负载分配：为每一种负载类型分配对应容量，并弃用超出限定值的请求。

6. 静态响应处理：在边缘位置直接建立部分响应，从而避免其转发到内部集群。

7. 多区域弹性：跨越AWS Region进行请求路由，旨在实现ELB（Elastic Load Balancing）使用的多样化，以及让系统的便越更贴近系统的使用者。

# 四.Ribbon
>Ribbon是Sping Cloud的一个组件，Spring Cloud Ribbon是一个负载均衡的解决方案，Ribbon是Netflix发布的负载均衡器，
>Spring对其进行了集成，Spring Cloud Ribbon是基于Netflix Ribbon实现的，是一个用于对HTTP请求进行控制的负载均衡客户端。
>Spring Cloud Ribbon也是要结合Eureka Server来使用的，因为也要在注册中心进行注册。
>在注册中心对Ribbon进行注册之后，Ribbon就可以基于某种负载均衡算法，如轮询、随机、加权轮询、加权随机等自动帮助服务消费者调用接口，
>开发者也可以根据具体需求自定义Ribbon负载均衡算法。实际开发中，Spring Cloud Eureka来使用，Eureka Server提供所有可以调用的服务提供者列表，Ribbon基于特定的负载均衡算法从这些服务提供者中选择要调用的具体实例。

示例图：
![ribbon结构图](https://oscimg.oschina.net/oscnet/up-3e5697960342d4f89d0e1d624bac91f339a.png)


# 五.Feign
>Feign也是去实现负载均衡，但是它的使用要比Ribbon更加简化，它实际上是基于Ribbon进行了封装，让我们可以通过调用接口的方式实现负载均衡。
>Feign和Ribbon都是由Netflix提供的，Feign是一个声明式、模板化的Web Service客户端，它简化了开发者编写Web服务客户端的操作，
>开发者可以通过简单的接口和注解来调用HTTP API，使得开发变得更加简化、快捷。Spring Cloud Feign也是基于Netflix Feign的二次开发，
>它整合了Ribbon和Hystrix，具有可插拔、基于注解、负载均衡、服务熔断等一系列的便捷功能，也就是说我们在实际开发中可以用Feign来取代Ribbon。

>相比较于Ribbon+RestTemplate的方式，Feign大大简化了代码的开发，Feign支持多种注解，包括Feign注解、JAX-RS注解、Spring MVC注解等，
>Spring Cloud对Feign进行了优化，整合了Ribbon和Eureka，从而让Feign使用更加方便。

**Ribbon和Feign的区别**
- Ribbon是一个通用的HTTP客户端工具，Feign是基于Ribbon实现的。

**Feign的优点**

1. Feign是一个声明式的Web Service客户端。

2. 支持Feign注解、Spring MVC注解、JAX-RS注解

3. Feign是基于Ribbon实现，使用起来更加方便

4. Feign集成了Hystrix，具备服务熔断的功能

# 六.Hystrix
>容错机制是指的是在一个分布式系统中，每个微服务之间是相互调用的，并且他们之间相互依赖，而实际的运行情况中，
>可能会因为各种原因导致某个微服务不可用，那么依赖于这个微服务的其他微服务就可能出现响应时间过长或者请求失败的情况，
>出现这种情况比较多就可能导致整个系统卡顿甚至奔溃。那么如何解决这个问题呢，就可以通过Hystix的容错机制来处理。

容错机制是指在不改变各个微服务的调用关系的前提下，针对错误情况进行预先处理。
Hystrix的主要作用就是当服务提供者发生故障无法访问的时候，向服务消费者返回一个fallback的降级处理，从而保证系统能顺利运行

结合Feign讲解Hystrix的容错机制，容错功能指的是当服务的调用发生问题的时候，我们做一个降级处理，用另外一部分代码进行处理。
可以有效的防止程序因为响应时间过长而造成整个系统崩溃，并且还能给用户提供更为友好的交互。但是Hystrix除了熔断机制外，
还提供了对请求的数据监控，本篇着重讲解Hystix的数据监控。Hystrix数据监控需要结合Spring Boot Actuator来使用，
Actuator提供了对服务的健康监控、数据统计，可以通过hystrix.stream节点获取监控请求数据，提供了可视化的监控界面。

**设计原则:**
1. 服务隔离机制：防止某个服务提供者出现故障而影响整个系统的运行。

2. 服务降级机制：当服务提供者出现故障时，向服务消费者返回一个fallback降级处理。

3. 熔断机制：当服务消费者请求失败率打到某个特定的数值时，会迅速的启动熔断机制，并且对错误进行修复。

4. 提供实时的监控和报警功能：保证熔断机制的运行。

5. 提供实时的配置修改功能：保证熔断机制的运行。

# 七.Config
>什么是配置中心呢，在基于微服务的分布式系统中，每个业务模块都可以拆分成独立自主的服务，由多个请求来协助完成某个需求，
>那么在某一具体的业务场景中，某一个请求需要调用多个服务来完成，那么就存在一个问题，多个服务所对应的配置项也是非常多的，
>每个服务都有自己的配置文件，如果某一个微服务配置进行了修改，那么其他的服务消费者也需要做出对应的调整，
>如果在每个服务消费者对应的配置文件中去修改的话是非常麻烦的，并且改完后每个服务还需要重新部署。
>这时候就需要把各个服务的配置进行统一管理，便于部署与维护，所以Spring Cloud提出了解决方法，就是Spring Cloud Config.

Spring Cloud Config可以通过服务端为多个客户端提供配置服务。Spring Cloud Config可以将配置文件存储在本地，
也可以将配置文件存储在远程仓库中。可以在不重启微服务的前提下来修改它的配置。具体操作就是创建Config Server，
通过它管理所有的配置文件。

# 八.Zipkin
>为什么要有服务跟踪，分布式系统中有很多个服务在相互调用，调用关系是错综复杂的，如果这时出现了问题，我们在进行问题排查的时候，
>或者在优化架构的时候，工作量就比较大，这时候就需要我们能够准确的跟踪到每个网络请求，了解整个运行流程，
>如网络请求经过了哪些微服务，在每个微服务里是否有延迟等信息，这样就方便我们排查问题，
>这时我们就可以使用Spring Cloud Zipkin来实现。

Spring Cloud Zipkin是一个可以采集并且跟踪分布式系统中请求数据的组件，让开发者可以更加直观的监控到请求在各个微服务所耗费的时间等，
Zipkin包括Zipkin Server服务端和Zipkin Client客户端。Zipkin Server服务端是用来采集微服务之间的追踪数据的，
通过Zipkin Client客户端完成数据的生成和展示