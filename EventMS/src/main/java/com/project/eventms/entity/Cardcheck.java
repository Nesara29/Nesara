package com.project.eventms.entity;

 
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cardcheck")
public class Cardcheck
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String cardtype;

    @Column(nullable=false)
    private String cardno ;
    
    @Column(nullable=false)
    private String expiry;
    
    @Column(nullable=false)
    private String cvvcode;
    
    @Column(nullable=false)
    private long valid;
    
    @Column(nullable=false)
    private String result;
}
