package com.scm.helper; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// Message class to hold the message content and type
public class Message {
    private String content;
    @Builder.Default
    private MessageType type = MessageType.blue;

}
