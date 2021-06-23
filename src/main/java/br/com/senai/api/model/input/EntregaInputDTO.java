package br.com.senai.api.model.input;

import br.com.senai.api.model.ClienteDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInputDTO {

    @Valid
    @NotNull
    private ClienteDTO pessoa;

    @Valid
    @NotNull
    private DestinatarioInputDTO destinatario;

    @NotNull
    private BigDecimal taxa;
}
