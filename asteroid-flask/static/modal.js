const modal = document.getElementById('asteroid-modal');

modal.addEventListener('show.bs.modal', () => {
  modal.style.display = 'block';
  modal.classList.add('show');
});

modal.addEventListener('hide.bs.modal', () => {
  modal.style.display = 'none';
  modal.classList.remove('show');
});

function getAndResetField(id) {
  const input = document.getElementById(id);
  if (input.tagName.toLowerCase() !== 'input') {
    throw new Error(`Invalid tag. Element with id '${id}' must have be an input tag.`);
  }

  if (input.getAttribute('type') === 'checkbox') {
    const isChecked = input.checked;
    input.checked = false;
    return isChecked;
  }
  const value = input.value;
  document.getElementById(id).value = '';

  if (value === '' || value === undefined) {
    throw new Error(`Missing value for field '${id}'`)
  }
  return input.getAttribute('type') === 'number' ? parseFloat(value) : value;
}

document.getElementById('asteroid-save-btn').onclick = async () => {
  try {
    const asteroid = {
      'name': getAndResetField('asteroid-name'),
      'diameter_min': getAndResetField('asteroid-diameter-min'),
      'diameter_max': getAndResetField('asteroid-diameter-max'),
      'hazard': getAndResetField('asteroid-hazard'),
      'relative_velocity': getAndResetField('asteroid-rel-velocity'),
      'distance': getAndResetField('asteroid-distance'),
      'orbiting_body': getAndResetField('asteroid-orbiting-body'),
    };
    console.debug(asteroid);

    const resp = await fetch('/api/v1/asteroids', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(asteroid),
    });
    if (!resp.ok) {
      throw new Error(`${resp.status} - ${resp.statusText}`);
    }

    const created_asteroid = await resp.json();
    console.debug(created_asteroid);
    location.reload();

  } catch (err) {
    console.error('Failed to save asteroid.', err);
  }
};
