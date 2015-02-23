package no.mesan.frisk.db

import java.sql.Timestamp

import no.mesan.frisk.model.frisk.consumeType.ConsumeType
import no.mesan.frisk.model.frisk.flavour.Flavour
import no.mesan.frisk.model.frisk.project.{UserProject, Project}
import no.mesan.frisk.model.user.User

/**
 * @author Knut Esten Melandsø Nekså
 */
object Seed {
  UserDao.insert(User(None, "Knut", "Knuffern", "passord", "knuffern"))
  UserDao.insert(User(None, "Anders", "Playboy", "passord", "playboy"))
  UserDao.insert(User(None, "Simen", "Drusern", "passord", "drusern"))
  UserDao.insert(User(None, "Mikkel", "Mikkelback", "passord", "mikkelback"))

  FlavourDao.insert(Flavour(None, "EXTRA STRONG", "Insanely strong frisk"))
  FlavourDao.insert(Flavour(None, "EUCA MENTHOL", "Refreshing Power Mints"))

  ConsumeTypeDao.insert(ConsumeType(None, "Bonusfrisk", 1))
  
  ProjectDao.insert(Project(None, "The Frisk Khalifa",
    new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), "Bygge verdens høyeste frisktårn"))

  ConsumeTypeDao.insert(ConsumeType(None, "Ordinær", 2))
  ConsumeTypeDao.insert(ConsumeType(None, "Singel bonusfrisk", 1))

//  LogDao.insert(Log(None, None, 1, 1, 1, 1))
  
  UserProjectDao.insert(new UserProject(1, 2))
  UserProjectDao.insert(new UserProject(2, 2))
  UserProjectDao.insert(new UserProject(3, 2))
  UserProjectDao.insert(new UserProject(4, 2))
}
