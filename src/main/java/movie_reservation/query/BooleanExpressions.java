package movie_reservation.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.NoArgsConstructor;
import movie_reservation.entities.*;
import movie_reservation.types.Casting;
import movie_reservation.types.Genre;

@NoArgsConstructor
public class BooleanExpressions {
    public BooleanExpression actorEq(Actor actor) {
        if(actor == null)
            return null;
        return QActorRole.actorRole.actor.eq(actor);
    }

    public BooleanExpression castingEq(Casting casting) {
        if(casting == null)
            return null;
        return QActorRole.actorRole.castingAs.eq(casting);
    }

    public BooleanExpression actorRoleEq(ActorRole actorRole) {
        if(actorRole == null)
            return null;
        return QMovie.movie.actorRoles.contains(actorRole);
    }

    public BooleanExpression directorEq(Director director) {
        if(director == null)
            return null;
        return QMovie.movie.direct00or.eq(director);
    }

    public BooleanExpression releaseDateContains(String year) {
        if(year == null || year.equals(""))
            return null;
        return QMovie.movie.releaseDate.contains(year);
    }

    public BooleanExpression genreEq(Genre genre) {
        if(genre == null)
            return null;
        return QMovie.movie.genre.eq(genre);
    }

    public BooleanExpression userNameEq(String userName) {
        if(userName == null || userName.equals(""))
            return null;
        return QUser.user.name.eq(userName);
    }
}
