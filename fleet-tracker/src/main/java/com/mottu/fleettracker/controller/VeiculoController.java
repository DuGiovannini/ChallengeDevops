package com.mottu.fleettracker.controller;

import com.mottu.fleettracker.model.Veiculo;
import com.mottu.fleettracker.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta classe é um controller REST
@RequestMapping("/api/veiculos") // Todas as URLs começarão com /api/veiculos
public class VeiculoController {

    @Autowired // Injeção de dependência: o Spring nos dá uma instância do Repository
    private VeiculoRepository veiculoRepository;

    // --- CRUD ---

    // 1. CREATE (Inclusão)
    @PostMapping
    public Veiculo criarVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    // 2. READ (Consulta Geral)
    @GetMapping
    public List<Veiculo> listarTodosVeiculos() {
        return veiculoRepository.findAll();
    }
    
    // 3. READ (Consulta por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable Long id) {
        return veiculoRepository.findById(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o veículo
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // 4. UPDATE (Alteração)
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoDetalhes) {
        return veiculoRepository.findById(id)
                .map(veiculoExistente -> {
                    veiculoExistente.setPlaca(veiculoDetalhes.getPlaca());
                    veiculoExistente.setModelo(veiculoDetalhes.getModelo());
                    veiculoExistente.setLatitude(veiculoDetalhes.getLatitude());
                    veiculoExistente.setLongitude(veiculoDetalhes.getLongitude());
                    Veiculo veiculoAtualizado = veiculoRepository.save(veiculoExistente);
                    return ResponseEntity.ok(veiculoAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE (Exclusão)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVeiculo(@PathVariable Long id) {
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculoRepository.delete(veiculo);
                    return ResponseEntity.ok().build(); // Retorna 200 OK sem corpo
                }).orElse(ResponseEntity.notFound().build());
    }
}