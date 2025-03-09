    package com.backend.models;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.io.Serializable;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "tb_inventory", uniqueConstraints = {
            @UniqueConstraint(columnNames = {"branch_id", "product_id"})
    })
    public class InventoryEntity extends BaseEntity implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "inventory_id")
        private Long inventoryId;

        @Column(name = "quantity")
        private int quantity;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private ProductEntity product;

        @ManyToOne
        @JoinColumn(name = "branch_id")
        private BranchEntity branch;
    }

