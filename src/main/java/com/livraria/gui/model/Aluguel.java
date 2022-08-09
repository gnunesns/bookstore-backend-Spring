package com.livraria.gui.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.livraria.gui.auditable.Auditable;
import com.livraria.gui.model.enums.AluguelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluguel")
public class Aluguel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnoreProperties("alugueis")
    @JoinColumn(name = "livro_id_fk")
    private Livro livro;
    @ManyToOne
    @JsonIgnoreProperties("alugueis")
    @JoinColumn(name = "usuario_id_fk")
    private Usuario usuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAluguel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPrevisao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    private AluguelStatus status;
}
