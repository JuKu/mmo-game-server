CREATE TABLE IF NOT EXISTS `${prefix}regions` (
`regionID` int(10) NOT NULL,
  `title` varchar(255) NOT NULL,
  `map_info_path` varchar(600) NOT NULL,
  `max_players` int(10) NOT NULL DEFAULT '200',
  `locked` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes für die Tabelle `regions`
--
ALTER TABLE `${prefix}regions`
 ADD PRIMARY KEY (`regionID`);

--
-- AUTO_INCREMENT für Tabelle `regions`
--
ALTER TABLE `${prefix}regions`
MODIFY `regionID` int(10) NOT NULL AUTO_INCREMENT;

CREATE TABLE IF NOT EXISTS `${prefix}characters` (
  `cid` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` enum('PLAYER','NPC') NOT NULL DEFAULT 'PLAYER',
  `userID` int(10) NOT NULL DEFAULT '-1',
  `data` text NOT NULL,
  `current_regionID` int(10) NOT NULL DEFAULT '1',
  `first_game` int(10) NOT NULL DEFAULT '1',
  `pos_x` int(10) NOT NULL DEFAULT '-1',
  `pos_y` int(10) NOT NULL DEFAULT '-1',
  `pos_z` int(10) NOT NULL DEFAULT '-1',
  `auto_join` int(10) NOT NULL DEFAULT '0',
  `visible` int(10) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes für die Tabelle `${prefix}characters`
--
ALTER TABLE `${prefix}characters`
 ADD PRIMARY KEY (`cid`), ADD KEY `userID` (`userID`), ADD KEY `current_regionID` (`current_regionID`), ADD KEY `auto_join` (`auto_join`);

--
-- AUTO_INCREMENT für Tabelle `${prefix}characters`
--
ALTER TABLE `${prefix}characters`
MODIFY `cid` int(10) NOT NULL AUTO_INCREMENT;

--
-- Tabellenstruktur für Tabelle `${prefix}users`
--

CREATE TABLE IF NOT EXISTS `${prefix}users` (
`userID` int(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `online` int(10) NOT NULL DEFAULT '0',
  `last_online` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `activated` int(10) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `${prefix}users`
--
ALTER TABLE `${prefix}users`
 ADD PRIMARY KEY (`userID`), ADD UNIQUE KEY `username` (`username`), ADD KEY `online` (`online`,`last_online`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `${prefix}users`
--
ALTER TABLE `${prefix}users`
MODIFY `userID` int(10) NOT NULL AUTO_INCREMENT;

--
-- Tabellenstruktur für Tabelle `${prefix}races`
--

CREATE TABLE IF NOT EXISTS `${prefix}races` (
`id` int(10) NOT NULL,
  `title` varchar(255) NOT NULL,
  `start_regionID` int(10) NOT NULL DEFAULT '1',
  `selectable` int(10) NOT NULL DEFAULT '1',
  `start_pos_x` float(10,4) NOT NULL,
  `start_pos_y` float(10,4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Indizes für die Tabelle `${prefix}races`
--
ALTER TABLE `${prefix}races`
 ADD PRIMARY KEY (`id`), ADD KEY `selectable` (`selectable`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `${prefix}races`
--
ALTER TABLE `${prefix}races`
MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;