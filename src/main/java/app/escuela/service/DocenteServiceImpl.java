package app.escuela.service;

import app.escuela.dao.DocenteDao;
import app.escuela.domain.Docente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService{

    @Autowired
    private DocenteDao docenteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Docente> listarDocentes() {
        return (List<Docente>)  docenteDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Docente docente) {
        docenteDao.save(docente);
    }

    @Override
    @Transactional
    public void eliminar(Docente docente) {
        docenteDao.delete(docente);
    }

    @Override
    @Transactional(readOnly = true)
    public Docente obtenerDocente(Docente docente) {
        return docenteDao.findById(docente.getIddocente()).orElse(null);
    }
}
