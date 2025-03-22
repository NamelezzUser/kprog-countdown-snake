# 🧠 Countdown & 🐍 Snake – Java Team Projects

This repository contains two Java-based projects developed in a team of three students during the second semester
of the Software Engineering program at Hochschule Heilbronn (Module: *Komplexe Programme*).  
We collaborated using GitLab, with a strong focus on version control workflows, code quality, and structured teamwork.

---

## [📁 Projects](app/src/main/java/livesession) + [JUnit](app/src/test/java/livesession)

### [🧠 Countdown (Console Application)](app/src/main/java/livesession/countdown)

A terminal-based application inspired by the British game show *Countdown*.  
The program calculates all possible arithmetic expressions from a set of five numbers to reach a given target number using basic operations (+, −, ×, ÷).

- **Key Challenges:** Designing and implementing an efficient algorithm to explore the solution space
- **Technologies Used:** Java, JUnit
- **Skills Applied:** Algorithm development, pseudocode design, test-driven development
- **Theory Applied:** Algorithms & Data Structures (thought in Semester 3) and parts of the [YouTube playlist by
  Abdul Bari](https://www.youtube.com/watch?v=0IAPZzGSbME&list=PLDN4rrl48XKpZkf03iYFl-O29szjTrs_O) (Professional Programmer and Educator).

---

### [🐍 Snake (JavaFX Game)](app/src/main/java/livesession/snake)

A real-time Snake game with a responsive JavaFX user interface.  
The project architecture follows the **MVVM** pattern and includes multithreaded game logic and dynamic user interaction.

- **Features:**
    - Live game loop (pause/resume using `wait()`/`notify()`)
    - Observable properties for score, game state and board
    - GUI elements (SnakeBoard, SnakeCell, SnakeDisplay)
    - Keyboard and button controls for navigation and game actions

- **Technologies Used:** Java, JavaFX, JUnit, Git, Multithreading
- **Skills Applied:** GUI development, MVVM architecture, observable state binding, concurrent programming

---

## 👥 Team Collaboration & Git Workflow

Both projects were implemented in a team of three using GitLab.  
We practiced Git-based collaboration with separate feature branches, merge requests, and conflict resolution.  
We studied Git fundamentals through [learngitbranching.js.org](https://learngitbranching.js.org/?locale=de_DE) as part of our self-guided learning.

---

# 🐍 Snake – Original Project Notes

## Getting Started

```
# Suggested Git setup:
cd existing_repo
git remote add origin https://git.it.hs-heilbronn.de/it/courses/seb/kprog/kprog-2023-ws.git
git branch -M main
git push -uf origin main
```

## Essential Git Commands

```
git status                  # Show status of current branch
git pull                    # Sync local branch with remote
git add ./foldername
git commit -m "Descriptive commit message"
git push                    # Always push to your own branch first
git merge branchname        # Merge the selected branch into the current one
```

## Useful Terminal Commands

```
cd foldername         # Navigate into a folder
ls                    # List folder contents
mkdir newFolder       # Create a new folder
rm filename           # Delete a file
```
---
## Project Introduction

Welcome to our Snake game project developed for the Komplexe Programme module.
We chose a *single-screen* design to keep the implementation focused and efficient. Below are our reasons:

## Design Choices

**Functionality First**: A minimal UI allowed us to concentrate on implementing core gameplay features.

**Time & Resource Efficiency**: Focused development within the project timeframe.

**User-Friendly**: One screen simplifies player interaction and enhances usability.

**Risk Minimization**: A simpler structure reduces potential bugs and improves maintainability.

## Mockup Implementation

While the original plan included multiple screens, we decided a single, state-driven interface was more effective.
UI elements are enabled or disabled depending on the game state, providing clear configuration and gameplay within one unified interface.

## Conclusion

This version of Snake lays a solid foundation for future expansion – including visual improvements, added features, and enhanced gameplay.

---


## License

This project is licensed under the [GNU General Public License v3.0](LICENSE). 

⚠️ Some files included in this project were provided as part of the university course material and are not subject to the license applied to this repository. 
Please refer to comments within individual files for details.

---