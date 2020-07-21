package com.marcoteixeira.cursomc.services;

import com.marcoteixeira.cursomc.domain.PagamentoBoleto;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoBoleto pagamentoBoleto, Date instantePedido){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(instantePedido);
        calendario.add(Calendar.DAY_OF_MONTH, 7);
        pagamentoBoleto.setDataVencimento(calendario.getTime());

    }

}
