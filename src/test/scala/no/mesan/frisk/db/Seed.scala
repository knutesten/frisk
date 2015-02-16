package no.mesan.frisk.db

import no.mesan.frisk.model.frisk.consumeType.ConsumeType
import no.mesan.frisk.model.frisk.flavour.Flavour
import no.mesan.frisk.model.frisk.log.Log
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

  ConsumeTypeDao.insert(ConsumeType(None, "Bonusfrisk", 1))

  LogDao.insert(Log(None, None, 1, 1, 1, 1))
}
