package com.mottu.fleettracker.repository;

import com.mottu.fleettracker.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Define a classe como um componente de repositório do Spring
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // É isso! A interface JpaRepository já nos dá os métodos:
    // save() -> para Inclusão e Alteração
    // findAll() -> para Consulta de todos
    // findById() -> para Consulta por ID
    // deleteById() -> para Exclusão
}