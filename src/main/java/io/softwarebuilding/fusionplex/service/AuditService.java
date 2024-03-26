package io.softwarebuilding.fusionplex.service;

import io.softwarebuilding.fusionplex.entity.Genre;
import io.softwarebuilding.fusionplex.entity.Title;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    private final Javers javers;

    @Autowired
    public AuditService( final Javers javers ) {
        this.javers = javers;
    }

    public String getGenresChanges() {
        final QueryBuilder jqlQuery = QueryBuilder.byClass( Genre.class );

        final List<Change> changes = this.javers.findChanges(jqlQuery.build());

        return this.javers.getJsonConverter().toJson( changes );
    }

    public String getTitleChanges() {
        final QueryBuilder jqlQuery = QueryBuilder.byClass( Title.class );

        final List<Change> changes = this.javers.findChanges(jqlQuery.build());

        return this.javers.getJsonConverter().toJson( changes );
    }

}
