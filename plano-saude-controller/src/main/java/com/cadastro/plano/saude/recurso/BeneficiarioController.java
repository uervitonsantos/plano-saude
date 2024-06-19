package com.cadastro.plano.saude.recurso;

import com.cadastro.plano.saude.dto.BeneficiarioDTO;
import com.cadastro.plano.saude.filterDTO.BeneficiarioFiltroDTO;
import com.cadastro.plano.saude.util.constates.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Beneficiário", description = "Operações para os recursos de beneficiários")
public interface BeneficiarioController extends Resource {

    @Operation(summary = "Retorna o beneficiário consultada pelo codigo identificador (id)", description = "Retorna um Beneficiário cadastrado", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na busca do beneficiário", implementation = BeneficiarioDTO.class))),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Código do Beneficiário invalido", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Beneficiário não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Beneficiário não autorizado", content = @Content)})
    ResponseEntity getBeneficiario(@Parameter(description = "Código identificador do Beneficiário") Long beneficiarioID);

    @Operation(summary = "Retorna lista de Beneficiários", description = "Retorna uma lista de todos os Beneficiários cadastrados", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(description = "Objeto Json retornado na busca do beneficiário", implementation = BeneficiarioDTO.class)))),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Beneficiário não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Beneficiário não autorizado", content = @Content)})
    ResponseEntity getBeneficiarios(@Parameter(description = "Filtros permitidos para a buscar de beneficiário") BeneficiarioFiltroDTO filtro);

    @Operation(summary = "Cria um novo beneficiário", description = "Cria um novo beneficiário e adiciona a lista de beneficiários cadastrados",
            requestBody = @RequestBody(description = "Dados necessarios para a  criação de um novo beneficiário", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON)), responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na criação do beneficiários", implementation = BeneficiarioDTO.class))),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao criar novo beneficiário", content = @Content)})
    ResponseEntity criaBeneficiario(BeneficiarioDTO dto);

    @Operation(summary = "Atualiza um beneficiário", description = "Atualiza um beneficiário cadastrado",
            requestBody = @RequestBody(description = "Dados necessarios para a  atualização de um beneficiário", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            responses = {
                    @ApiResponse(responseCode = Resource.RESPONSE_OK, description = "Sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(description = "Objeto Json retornado na atualização do beneficiário", implementation = BeneficiarioDTO.class))),
                    @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao atualizar o beneficiário", content = @Content),
                    @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Beneficiário não autorizado", content = @Content),
                    @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Beneficiário não autorizado", content = @Content)})
    ResponseEntity atualizaBeneficiario(@Parameter(description = "Código identificador do beneficiário") Long beneficiarioID, BeneficiarioDTO dto);

    @Operation(summary = "Remove um beneficiário", description = "Remove um beneficiário cadastrado", responses = {
            @ApiResponse(responseCode = Resource.RESPONSE_NO_CONTENT, description = "Beneficiário removido com sucesso"),
            @ApiResponse(responseCode = Resource.RESPONSE_BAD_REQUEST, description = "Erro ao remover beneficiário", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_UNAUTHORIZED, description = "Beneficiário não autorizado", content = @Content),
            @ApiResponse(responseCode = Resource.RESPONSE_FORBIDDEN, description = "Beneficiário não autorizado", content = @Content)})
    ResponseEntity removeBeneficiario(@Parameter(description = "Código identificador do beneficiário") Long beneficiarioID);
}
