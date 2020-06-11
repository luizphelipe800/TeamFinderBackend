package com.teamfinder.dbs;

import com.mysql.jdbc.Connection;
import com.teamfinder.beans.EventoBean;
import com.teamfinder.utils.ConexaoDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventoDao {
    private Connection banco;

    public EventoDao() throws SQLException, ClassNotFoundException {
        this.banco = (Connection) new ConexaoDB().getConnection();
    }

    public List<EventoBean> listar () throws SQLException {
        List<EventoBean> eventos = new ArrayList<>();

        String sql = "SELECT * FROM ev_eventos";
        PreparedStatement stmt = banco.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();

        while(result.next()){
            EventoBean evento = new EventoBean(
                result.getInt(1), 
                result.getString(2), 
                result.getInt(3), 
                result.getInt(4)
            );

            eventos.add(evento);
        }

        return eventos;
    }

    public EventoBean buscar (EventoBean evento) throws SQLException {
        String sql = "SELECT * FROM ev_evento WHERE ev_id = ?";
        PreparedStatement stmt = banco.prepareStatement(sql);
        stmt.setInt(1, evento.getId());
        ResultSet result = stmt.executeQuery();

        while(result.next()){
            evento.setId(result.getInt(1));
            evento.setEquipeId(result.getInt(2));
            evento.setEnderecoId(result.getInt(3));
            evento.setNome(result.getString(4));
        }

        return evento;
    }
}