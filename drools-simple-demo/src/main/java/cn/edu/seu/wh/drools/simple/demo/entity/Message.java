package cn.edu.seu.wh.drools.simple.demo.entity;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    public static final int HELLO=0;
    public static final int GOODBYE=1;
    private String message;
    private int status;
}
