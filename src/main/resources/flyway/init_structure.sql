CREATE TABLE employee
(
  id        BIGSERIAL NOT NULL
    CONSTRAINT employee_pkey
    PRIMARY KEY,
  firstname VARCHAR(255),
  lastname  VARCHAR(255)
);

CREATE TABLE team
(
  id    BIGSERIAL NOT NULL
    CONSTRAINT team_pkey
    PRIMARY KEY,
  p1_id BIGINT
    CONSTRAINT fk_team_p1_id
    REFERENCES employee,
  p2_id BIGINT
    CONSTRAINT fk_team_p2_id
    REFERENCES employee
);

CREATE TABLE match
(
  id       BIGSERIAL NOT NULL
    CONSTRAINT match_pkey
    PRIMARY KEY,
  goals1   INTEGER,
  goals2   INTEGER,
  team1_id BIGINT
    CONSTRAINT fk_match_team1_id
    REFERENCES team,
  team2_id BIGINT
    CONSTRAINT fk_match_team2_id
    REFERENCES team
);


CREATE SEQUENCE public.employee_id_seq
  NO MINVALUE
  NO MAXVALUE
  NO CYCLE;
ALTER TABLE public.employee
  ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq');
ALTER SEQUENCE public.employee_id_seq
OWNED BY public.employee.id;

CREATE SEQUENCE public.match_id_seq
  NO MINVALUE
  NO MAXVALUE
  NO CYCLE;
ALTER TABLE public.match
  ALTER COLUMN id SET DEFAULT nextval('public.match_id_seq');
ALTER SEQUENCE public.match_id_seq
OWNED BY public.match.id;

CREATE SEQUENCE public.team_id_seq
  NO MINVALUE
  NO MAXVALUE
  NO CYCLE;
ALTER TABLE public.team
  ALTER COLUMN id SET DEFAULT nextval('public.team_id_seq');
ALTER SEQUENCE public.team_id_seq
OWNED BY public.team.id;