package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Localizacao {

    private String longitude;
    private String latitude;
}
