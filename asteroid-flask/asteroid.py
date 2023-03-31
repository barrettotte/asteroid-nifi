from dataclasses import dataclass
from typing import Optional

@dataclass
class Asteroid:
    name: str
    diameter_min: float
    diameter_max: float
    hazard: bool
    rel_velocity: float
    distance: float
    orbiting_body: str
    created: str
    id: Optional[int] = None # set after mongodb insert
