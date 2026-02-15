# Emotion Tracker App - Project Overview

## Purpose

A minimalist Android application for tracking emotional states throughout the day using Plutchik's Wheel of Emotions model. The app prioritizes speed and simplicity, enabling users to log their current emotion in just 2 taps without journaling or complex workflows.

## Target User

Individuals who want to:
- Track emotional patterns over time
- Log emotions quickly (3x per day or more)
- Avoid lengthy journaling or evaluation scales
- Share emotion data with therapists or for self-reflection
- Use categorical emotions rather than good/bad scales

## Key Features

**Core Functionality:**
- Two-tap emotion logging using visual wheel interface
- 8 primary emotions with 3 intensity levels each (24 total states)
- Multiple logs per day supported
- Automatic timestamp recording

**Data Management:**
- Local storage only (no cloud, no internet required)
- Automatic backup via Android Auto Backup
- Manual export for sharing (CSV, formatted text/PDF)
- Import capability for data migration

**Review Tools:**
- Calendar view showing emotions by date
- Chronological history list
- Edit and delete logged entries

**Quality of Life:**
- Configurable daily alarms that open the app
- Multiple color schemes
- Russian and English language support
- Settings for behavior customization

## Development Philosophy

- **Simplicity first**: No feature bloat, focused on core use case
- **Speed**: Complete a log in under 5 seconds
- **Privacy**: All data local, no tracking, no accounts
- **Reliability**: Data never lost, alarms work consistently
- **Minimal maintenance**: No plans for extensive feature expansion

## Timeline

This is a personal project with no fixed deadline. Development will be exploratory and learning-focused.

## Platform

Android only (API level 26+). No iOS or cross-platform version planned.

## Licensing

**Open Source:** MIT License  
**Repository:** Public GitHub repository  
**Distribution:** Google Play Store and/or GitHub Releases

The project will be open source to:
- Build trust for privacy-focused emotion tracking
- Enable community contributions and code review
- Provide portfolio value and learning opportunities
- Maintain full flexibility for future monetization

All monetization options remain open with MIT License (Play Store fees, premium features, services, donations).

---

## Scientific Background: Plutchik's Wheel of Emotions

### Original Work

**Primary Source:**
- Plutchik, R. (1980). "A General Psychoevolutionary Theory of Emotion." In R. Plutchik & H. Kellerman (Eds.), *Emotion: Theory, Research, and Experience: Volume 1. Theories of Emotion* (pp. 3-33). New York: Academic Press.
  - [Available on ScienceDirect](https://www.sciencedirect.com/science/article/abs/pii/B9780125587013500077)
  - [Semantic Scholar entry](https://www.semanticscholar.org/paper/A-GENERAL-PSYCHOEVOLUTIONARY-THEORY-OF-EMOTION-Plutchik/b3b7b374eee3ea3c6fe35c1e7bdf9bf24c9456b0) (1,954+ citations)

### Theory Fundamentals

Plutchik's psychoevolutionary theory is built on ten core postulates that integrate evolutionary biology, neuroscience, and psychology:

1. Emotions exist across all evolutionary levels (animals and humans)
2. Emotions have evolutionary history with species-specific expressions
3. Emotions served adaptive survival functions
4. Common emotional prototypes exist across species
5. A small number of basic, primary emotions exist
6. All other emotions are mixtures of primary emotions
7. Primary emotions are theoretical constructs inferred from behavior
8. Primary emotions exist in polar opposite pairs
9. Emotions vary in similarity to one another
10. Each emotion varies in intensity/arousal level

**The Eight Primary Emotions:**
- Joy ↔ Sadness
- Trust ↔ Disgust
- Fear ↔ Anger
- Surprise ↔ Anticipation

These are organized in a wheel structure showing relationships, opposites, and combinations.

### Additional Research

**Measurement and Application:**
- Plutchik, R. (1989). "Measuring Emotions and Their Derivatives." In R. Plutchik & H. Kellerman (Eds.), *Emotion: Theory, Research, and Experience: Volume 4. The Measurement of Emotions* (pp. 1-35).
  - [PDF available](https://2024.sci-hub.se/7492/10ad39e461fc35a2582e7a4e8acdc68b/plutchik1989.pdf)
  - Establishes principles for emotion measurement via self-report

**Later Synthesis:**
- Plutchik, R. (2001). "The Nature of Emotions." *American Scientist*, 89(4), 344-350.
  - [PDF available](https://emotionalcompetency.com/papers/plutchiknatureofemotions%202001.pdf)
  - Accessible overview of the theory and its therapeutic applications
  - Addresses the challenge of 90+ competing definitions of "emotion"

**Modern Validation:**
- Cowen, A. S., & Keltner, D. (2021). "Semantic Space Theory: A Computational Approach to Emotion." *PLOS ONE*, 16(11).
  - [Available on PLOS ONE](https://journals.plos.org/plosone/article?id=10.1371%2Fjournal.pone.0256503)
  - Modern computational validation and extension of emotion wheel concepts

**Comparative Framework:**
- Scherer, K. R., Shuman, V., et al. (2013). "The GRID meets the Wheel: Assessing Emotional Feeling via Self-Report." In J. J. R. Fontaine, K. R. Scherer, & C. Soriano (Eds.), *Components of Emotional Meaning*.
  - [Semantic Scholar entry](https://www.semanticscholar.org/paper/The-GRID-meets-the-wheel:-assessing-emotional-via-Scherer-Shuman/032d8f32c1b7d40ee12981ebda51229578e75f32)
  - Compares Plutchik's wheel to Geneva Emotion Wheel (GEW)
  - Validates wheel-based emotion measurement approaches

### Why Plutchik's Model for This App

**Strengths for Quick Logging:**
- Limited set of clear categories (8 primary emotions)
- Intensity levels provide nuance without overwhelming choice
- Visual wheel organization aids quick recognition
- Categorical rather than evaluative (avoids "good/bad" framing)
- Scientifically grounded with extensive validation
- Cross-culturally applicable basic emotions

**Therapeutic Value:**
Plutchik emphasized that understanding emotions scientifically is crucial for therapy, as emotional distress is the primary concern of psychotherapy. A valid measurement and conceptualization system enables better self-awareness and therapeutic dialogue.

### Alternative Theories and Emotion Models

While Plutchik's model provides the framework for this app, understanding competing theories offers broader perspective on emotion science.

#### Theory of Constructed Emotion (Barrett)

**Lisa Feldman Barrett's Theory of Constructed Emotion** represents a paradigm shift from classical basic emotions approaches.

**Key Publications:**

- **Barrett, L. F. (2017).** "The theory of constructed emotion: An active inference account of interoception and categorization." *Social Cognitive and Affective Neuroscience*, 12(1), 1-23.
  - [Open access PDF](https://affective-science.org/pubs/2017/barrett-tce-scan-2017.pdf)
  - [PubMed Central](https://ncbi.nlm.nih.gov/pmc/articles/PMC5390700/)
  - [Semantic Scholar entry](https://www.semanticscholar.org/paper/The-theory-of-constructed-emotion%3A-an-active-of-and-Barrett/b7921804881f92311533536fcc8f953908d2108c)

- **Barrett, L. F. (2017).** *How Emotions Are Made: The Secret Life of the Brain*. Boston: Houghton Mifflin Harcourt.
  - Popular science book presenting the theory for general audiences

**Core Argument:**

Rather than viewing emotions as fixed, universal biological categories (like "anger" or "fear"), Barrett proposes that emotions are **constructed in the moment** by the brain through:
- **Predictive coding** - the brain predicts what sensations mean based on past experience
- **Interoception** - sensing internal bodily states
- **Categorization** - labeling bodily sensations as specific emotions based on context
- **Degeneracy** - the same emotion can arise from different neural/physiological patterns

**Implication:** There are no universal "fingerprints" for emotions. The same physiological state can be experienced as different emotions depending on context, and the same emotion label can arise from different bodily states.

**Contrast with Plutchik:** Where Plutchik sees emotions as biologically basic and universal, Barrett argues they are context-dependent constructions shaped by culture and individual experience.

#### Alternative Basic Emotion Models

Different researchers have proposed varying numbers and types of "basic" emotions:

**Ekman's Six Emotions (1970s):**
1. Happiness
2. Sadness
3. Fear
4. Anger
5. Surprise
6. Disgust

**Notes:** This is essentially a subset of Plutchik (missing Trust and Anticipation). Ekman's cross-cultural facial expression research in Papua New Guinea provided early evidence for emotion universality.

**Izard's Ten Emotions (1977):**
1. Interest
2. Joy
3. Surprise
4. Sadness
5. Anger
6. Disgust
7. Contempt
8. Fear
9. Shame
10. Guilt

**Notes:** Includes emotions NOT in Plutchik:
- **Interest** - curiosity, engagement
- **Contempt** - scorn, disdain
- **Shame** - embarrassment, humiliation
- **Guilt** - self-reproach, remorse

Izard's Differential Emotions Theory (DET) emphasizes that emotions emerge at different developmental stages and have distinct neural substrates and facial expressions.

**Panksepp's Seven Affective Systems (1998):**
1. **SEEKING** (Expectancy) - motivation, curiosity, investigation
2. **FEAR** (Anxiety) - fight/flight/freeze responses
3. **RAGE** (Anger) - defensive aggression
4. **LUST** (Sexual excitement) - mating behaviors
5. **CARE** (Nurturance) - love, tenderness, caretaking
6. **PANIC/GRIEF** (Sadness) - separation distress, loneliness
7. **PLAY** (Social joy) - playfulness, laughter, social bonding

**Notes:** Very different from Plutchik's model:
- Based on mammalian subcortical brain systems (neuroscience-first approach)
- Includes **LUST, CARE, and PLAY** - not in most basic emotion models
- **SEEKING** is somewhat like Anticipation but broader (dopamine-driven exploration)
- Uses different terminology (RAGE vs Anger, PANIC/GRIEF vs Sadness)

**Key Reference:**
- Panksepp, J. (1998). *Affective Neuroscience: The Foundations of Human and Animal Emotions*. New York: Oxford University Press.
- [Selected Principles](https://pmc.ncbi.nlm.nih.gov/articles/PMC6344464/)

**Comparison Review:**
- Tracy, J. L., & Randles, D. (2011). "Four Models of Basic Emotions: A Review of Ekman and Cordaro, Izard, Levenson, and Panksepp and Watt." *Emotion Review*, 3(4), 397-405.
  - [Sage Journals](https://journals.sagepub.com/doi/10.1177/1754073911410747)
  - Compares major basic emotion theories and their convergences/disagreements

#### Current Scientific Consensus

**There is no consensus** on which emotions are "basic" or how many exist. The field is moving toward:
- **Dimensional models** (emotions as points in continuous space)
- **Context-dependent approaches** (emotions vary with situations)
- **Higher-dimensional taxonomies** (more complex than simple categories)

**For this app:** Plutchik's model remains appropriate as a practical framework for self-reflection, even as scientific debate continues. The goal is pattern recognition over time, not absolute measurement.

### Further Reading

For deeper understanding, consider:
- Plutchik's complete book: *Emotions and Life: Perspectives from Psychology, Biology, and Evolution* (2002)
- Wikipedia overview: [Robert Plutchik](https://en.wikipedia.org/wiki/Robert_Plutchik)
- Six Seconds educational resource: [Plutchik's Wheel of Emotions Explained](https://6seconds.org/2025/02/06/plutchik-wheel-emotions)

---

*Project created: 2026-02-14*
*Last updated: 2026-02-14*
