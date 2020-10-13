package mall;

import mall.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
   // @StreamListener(KafkaProcessor.INPUT)
    /*public void onStringEventListener(@Payload String eventString){
        System.out.println("@@@@@@@@@@@@@@onStringEventListener@@@@@@@@@@@@@@");
    }*/

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Ordered ordered){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if(ordered.isMe()){
            System.out.println("##### listener Ship : " + ordered.toJson());
            //주문 들어오면 주문 insert 작성해얌... delivery 또한.?
            //배송업체와 전문 교환
            //고객에게 SNS 배송시작 알림
            //배송정보 record (Aggregate record)등록
            Delivery delivery = new Delivery();
            delivery.setOrderId(ordered.getId());
            //status 값은 생성된게 없다. 하드 코딩.
            delivery.setStatus("Shipped");
            deliveryRepository.save(delivery);
            System.out.println("##### deliveryRepository.save(delivery) : " + delivery.toString());

        }
    }

}
