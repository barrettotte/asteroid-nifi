from dataclasses import dataclass
from typing import Optional

@dataclass
class Asteroid:
    name: str
    diameter_min: float
    diameter_max: float
    hazard: bool
    relative_velocity: float
    distance: float
    orbiting_body: str
    created: str
    created_by: str = 'asteroid-flask'
    id: Optional[str] = None # set after mongodb insert
