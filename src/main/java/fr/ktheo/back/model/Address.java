package fr.ktheo.back.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String town;
    @NonNull
    private int streetNumber;

    private String streetNumberComplement;
    @NonNull
    private String streetName;
    @NonNull
    private int postalCode;

    @NonNull
    @ManyToOne
    private User user;


    public Address(long id, String town, int streetNumber, String streetName, int postalCode, User user) {
    }
}
