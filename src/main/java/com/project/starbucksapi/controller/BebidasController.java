package com.project.starbucksapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.starbucksapi.model.Bebidas;
import com.project.starbucksapi.service.BebidasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bebidas")
@Api(tags = "Bebidas", description = "Cadastro de Bebidas")

@RequiredArgsConstructor
public class BebidasController {

	private final BebidasService bebidasService;
	
    @ApiOperation("Retorna bebidas cadastradas.")
    @ApiParam(name = "Authorization", type = "header")
	@GetMapping(value = "/consultar")
	public List<Bebidas> listBebidas() {
	        return bebidasService.findAll();
	}
	
    @ApiOperation("Retorna bebidas cadastradas.")
    @ApiParam(name = "Authorization", type = "header")
	@PostMapping(value = "/incluir")
	public void criarBebida(
		@RequestParam(value = "Identificacão", required = true) Long id,
		@RequestParam(value = "Nome", required = false) String name) {

		bebidasService.save(Bebidas
				.builder()
					.id(id)
					.name(name)
				.build());
	}
}
