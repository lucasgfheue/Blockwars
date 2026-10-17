# BlockWars

Mod para **Minecraft 1.21.1 + NeoForge**: mechas de guerra pilotables.

## Controles (iguales para todos los mechas)
| Tecla | Acción |
|---|---|
| Clic derecho sobre el mecha | Subirse |
| WASD | Caminar (sube bloques de 1,5 de alto) |
| ESPACIO | Salto con carga (Vanguard: **mantener** para volar con los propulsores) |
| **R** | Arma principal |
| **G** | Arma secundaria |
| SHIFT | Bajarse |
| Clic derecho con lingote de hierro | Reparar (+5 corazones) |
| SHIFT + clic derecho con la mano vacía | Guardarlo de nuevo como núcleo |

R y G se pueden cambiar en *Opciones → Controles → BlockWars*.
Mientras pilotás, el mecha recibe el daño en tu lugar.

## Los mechas
| | Vida / Armadura | R | G | Extra |
|---|---|---|---|---|
| **Unidad 01 · Mecha** | 120 / 12 | Cañón de bolas de fuego | — | Cabina abierta |
| **Unidad 02 · Goliath** | 200 / 18 | Ametralladora rotativa (8 balas) | 4 misiles | Escudo: −40 % de daño frontal |
| **Unidad 03 · Vanguard** | 150 / 14 | Espada de energía (corte en arco) | Bláster de muñeca | Vuela con propulsores, partes que brillan |
| **Unidad 04 · Bastión** | 260 / 22 | Autocañón (ráfaga de 6) | Salva de 6 cohetes | Humo de escape, casquillos |

Los misiles y cohetes rompen bloques solo si la regla `mobGriefing` está activada.

## Recetas (núcleos)
- **Mecha:** Bloque de hierro ×4, Bloque de redstone, Diamante, Pistón ×2, Alto horno
- **Goliath:** Bloque de hierro ×4, Ballesta, Diamante, Pistón ×2, Alto horno
- **Vanguard:** Bloque de hierro ×4, Espada de diamante, Bloque de diamante, Membrana de phantom ×2, Alto horno
- **Bastión:** Bloque de hierro ×4, TNT, Bloque de diamante, Pistón ×2, Alto horno

## Cómo compilar en GitHub
1. Subí **todo el contenido** de esta carpeta a un repositorio (incluidas la carpeta `.github` y `gradle/wrapper/gradle-wrapper.jar`).
2. Entrá a la pestaña **Actions**: el build arranca solo.
3. Al terminar (✅), descargá **blockwars-mod** en *Artifacts* → adentro está `blockwars-1.1.0.jar`.
4. Copiá el `.jar` a la carpeta `mods` de Minecraft con NeoForge 1.21.1.
