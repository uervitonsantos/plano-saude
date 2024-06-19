package com.cadastro.plano.saude;

import com.cadastro.plano.saude.entidade.Beneficiario;
import com.cadastro.plano.saude.entidade.Documento;
import com.cadastro.plano.saude.enumerated.TipoDocumentoEnum;
import com.cadastro.plano.saude.repository.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class beneficiarioInitial implements CommandLineRunner {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Override
    public void run(String... args) throws Exception {

        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Cria e Salvar o Beneficiário 1
        Beneficiario beneficiario1 = new Beneficiario();
        beneficiario1.setNomeBeneficiario("Uerviton Silva dos Santos");
        beneficiario1.setTelefone("1137326584");
        String dataNascimentoString1 = "15-06-1988";
        LocalDate dataNascimento1 = LocalDate.parse(dataNascimentoString1, formatter);
        beneficiario1.setDatanascimento(dataNascimento1);
        beneficiario1.setDataInclusaoBeneficiario(dataAtual);
        beneficiario1.setDataAtuazacaoBeneficiario(dataAtual);
        Beneficiario beneficiarioSalvo1 = beneficiarioRepository.salvaBeneficiario(beneficiario1);

        // Cria e Salvar o Documentos do Beneficiário 1
        Documento doc1B1 = new Documento();
        doc1B1.setBeneficiarioID(beneficiarioSalvo1.getBeneficiarioID());
        doc1B1.setTipoDocumento(TipoDocumentoEnum.valueOf("CPF"));
        doc1B1.setNumeroDocumento("123.456.789-00");
        doc1B1.setDescricao("Dcumento do beneficiario 1");
        doc1B1.setDataInclusaoDocumento(dataAtual);
        doc1B1.setDataAtuazacaoDocumento(dataAtual);
        doc1B1.setBeneficiario(beneficiarioSalvo1);

        Documento doc2B1 = new Documento();
        doc2B1.setBeneficiarioID(beneficiarioSalvo1.getBeneficiarioID());
        doc2B1.setTipoDocumento(TipoDocumentoEnum.valueOf("RG"));
        doc2B1.setNumeroDocumento("12.345.678-9");
        doc2B1.setDescricao("Dcumento do beneficiario 1");
        doc2B1.setDataInclusaoDocumento(dataAtual);
        doc2B1.setDataAtuazacaoDocumento(dataAtual);
        doc2B1.setBeneficiario(beneficiarioSalvo1);

        List<Documento> documentosB1 = new ArrayList<>();
        documentosB1.add(doc1B1);
        documentosB1.add(doc2B1);

        beneficiarioSalvo1.setDocumentos(documentosB1);
        beneficiarioRepository.merge(beneficiarioSalvo1);


        //***********************************************

        // Cria e Salvar o Beneficiário 2
        Beneficiario beneficiario2 = new Beneficiario();
        beneficiario2.setNomeBeneficiario("João dos Santos");
        beneficiario2.setTelefone("1158322483");
        String dataNascimentoString2 = "11-02-1978";
        LocalDate dataNascimento2 = LocalDate.parse(dataNascimentoString2, formatter);
        beneficiario2.setDatanascimento(dataNascimento2);
        beneficiario2.setDataInclusaoBeneficiario(dataAtual);
        beneficiario2.setDataAtuazacaoBeneficiario(dataAtual);
        Beneficiario beneficiarioSalvo2 = beneficiarioRepository.salvaBeneficiario(beneficiario2);

        // Cria e Salvar o Documentos do Beneficiário 2
        Documento doc1B2 = new Documento();
        doc1B2.setBeneficiarioID(beneficiarioSalvo2.getBeneficiarioID());
        doc1B2.setTipoDocumento(TipoDocumentoEnum.valueOf("CPF"));
        doc1B2.setNumeroDocumento("143.486.989-10");
        doc1B2.setDescricao("Dcumento do beneficiario 2");
        doc1B2.setDataInclusaoDocumento(dataAtual);
        doc1B2.setDataAtuazacaoDocumento(dataAtual);
        doc1B2.setBeneficiario(beneficiarioSalvo2);

        Documento doc2B2 = new Documento();
        doc2B2.setBeneficiarioID(beneficiarioSalvo2.getBeneficiarioID());
        doc2B2.setTipoDocumento(TipoDocumentoEnum.valueOf("RG"));
        doc2B2.setNumeroDocumento("15.385.978-10");
        doc2B2.setDescricao("Dcumento do beneficiario 2");
        doc2B2.setDataInclusaoDocumento(dataAtual);
        doc2B2.setDataAtuazacaoDocumento(dataAtual);
        doc2B2.setBeneficiario(beneficiarioSalvo2);

        List<Documento> documentosB2 = new ArrayList<>();
        documentosB2.add(doc1B2);
        documentosB2.add(doc2B2);

        beneficiarioSalvo2.setDocumentos(documentosB2);
        beneficiarioRepository.merge(beneficiarioSalvo2);


        //***********************************************

        // Cria e Salvar o Beneficiário 3
        Beneficiario beneficiario3 = new Beneficiario();
        beneficiario3.setNomeBeneficiario("Marcos dos Santos");
        beneficiario3.setTelefone("1136825483");
        String dataNascimentoString3 = "19-07-1998";
        LocalDate dataNascimento3 = LocalDate.parse(dataNascimentoString3, formatter);
        beneficiario3.setDatanascimento(dataNascimento3);
        beneficiario3.setDataInclusaoBeneficiario(dataAtual);
        beneficiario3.setDataAtuazacaoBeneficiario(dataAtual);
        Beneficiario beneficiarioSalvo3 = beneficiarioRepository.salvaBeneficiario(beneficiario3);

        // Cria e Salvar o Documentos do Beneficiário 3
        Documento doc1B3 = new Documento();
        doc1B3.setBeneficiarioID(beneficiarioSalvo3.getBeneficiarioID());
        doc1B3.setTipoDocumento(TipoDocumentoEnum.valueOf("CPF"));
        doc1B3.setNumeroDocumento("383.656.948-10");
        doc1B3.setDescricao("Dcumento do beneficiario 3");
        doc1B3.setDataInclusaoDocumento(dataAtual);
        doc1B3.setDataAtuazacaoDocumento(dataAtual);
        doc1B3.setBeneficiario(beneficiarioSalvo3);

        Documento doc2B3 = new Documento();
        doc2B3.setBeneficiarioID(beneficiarioSalvo3.getBeneficiarioID());
        doc2B3.setTipoDocumento(TipoDocumentoEnum.valueOf("RG"));
        doc2B3.setNumeroDocumento("17.384.288-02");
        doc2B3.setDescricao("Dcumento do beneficiario 2");
        doc2B3.setDataInclusaoDocumento(dataAtual);
        doc2B3.setDataAtuazacaoDocumento(dataAtual);
        doc2B3.setBeneficiario(beneficiarioSalvo3);

        List<Documento> documentosB3 = new ArrayList<>();
        documentosB3.add(doc1B3);
        documentosB3.add(doc2B3);

        beneficiarioSalvo3.setDocumentos(documentosB3);
        beneficiarioRepository.merge(beneficiarioSalvo3);

    }
}
