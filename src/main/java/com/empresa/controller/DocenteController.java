package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Docente;
import com.empresa.entity.Filtro;
import com.empresa.service.DocenteService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/docente")
@CrossOrigin(origins = "http://localhost:4200")
public class DocenteController {

	@Autowired
	private DocenteService docenteService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocente() {
		List<Docente> lista = docenteService.listaDocente();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDocente(@RequestBody Docente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Docente objSalida = docenteService.insertaActualizaDocente(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	//Listar docentes por nombre
	@GetMapping("/porNombre/{paramNombre}")
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocentePorNombre(@PathVariable("paramNombre") String filtro) {
		List<Docente> lista = docenteService.listaDocentePorNombreLike(filtro + "%");
		return ResponseEntity.ok(lista);
	}

	//Listar docentes por nombre
	@GetMapping("/porDni/{paramDni}")
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocentePorDni(@PathVariable("paramDni") String dni) {
		List<Docente> lista = docenteService.listaDocentePorDni(dni);
		return ResponseEntity.ok(lista);
	}
		
	//Listar docentes por nombre
	@GetMapping("/porNombreDni/{paramNombre}/{paramDni}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocentePorNombreDni(@PathVariable("paramNombre") String nombre, @PathVariable("paramDni") String dni) {
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService.listaDocentePorNombreDni(nombre + "%", dni);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe datos");
			}else {
				salida.put("lista", lista);
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en la consulta " + e.getMessage());
			e.printStackTrace();
		} 
		return ResponseEntity.ok(salida);
	}
	
	//Listar docentes usando parametros
	@GetMapping("/porNombreDniPorParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocentePorNombreDniParametros(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "dni") String dni) {
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService.listaDocentePorNombreDni(nombre + "%", dni);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe datos");
			}else {
				salida.put("lista", lista);
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en la consulta " + e.getMessage());
			e.printStackTrace();
		} 
		return ResponseEntity.ok(salida);
	}
	
	//Enviar parametros en formato JSON
	@GetMapping("/porNombreDniPorJson")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocentePorNombreDniJson(@RequestBody Filtro obj) {
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService.listaDocentePorNombreDni(obj.getNombre() + "%", obj.getDni());
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe datos");
			}else {
				salida.put("lista", lista);
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en la consulta " + e.getMessage());
			e.printStackTrace();
		} 
		return ResponseEntity.ok(salida);
	}
}
