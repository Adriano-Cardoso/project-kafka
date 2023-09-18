package com.adriano.strproducer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class StringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("str-topic", message)
                .whenComplete(((stringStringSendResult, throwable) -> {
                    if (Objects.isNull(stringStringSendResult)) {
                        log.info("Erro ao enviar mensagem {}", message);
                    } else {
                        log.info("Send message with success {}", message);
                        log.info("Partition {}, offset {}", stringStringSendResult.getRecordMetadata().partition(),
                                stringStringSendResult.getRecordMetadata().partition());

                    }

                }));
    }
}
