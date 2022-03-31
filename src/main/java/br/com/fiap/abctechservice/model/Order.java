package br.com.fiap.abctechservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId;

    @ManyToMany
    private List<Assistance> services;

    @OneToOne
    @JoinColumn(name = "start_order_location_id")
    private OrderLocation startOrderLocation;

    @OneToOne
    @JoinColumn(name = "end_order_location_id")
    private OrderLocation endOrderLocation;

    public boolean hasMinAssists() {
        return services.size() > 0;
    }

    public boolean exceedsMaxAssists() {
        return services.size() > 15;
    }
}
