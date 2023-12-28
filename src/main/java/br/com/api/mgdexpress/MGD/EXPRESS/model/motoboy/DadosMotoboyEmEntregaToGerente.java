package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

public record DadosMotoboyEmEntregaToGerente( Position position, String title) {
    public DadosMotoboyEmEntregaToGerente(DadosMotoboyList item) {
        this(new Position(Double.parseDouble(item.localizacao().getLatitude()),Double.parseDouble(item.localizacao().getLongitude())),item.nome());
    }
}

record Position(Double lat,Double lng){}
