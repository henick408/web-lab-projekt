"use client";
import axios from "axios";
import { useState, useEffect, useRef } from "react";
import RoundPattern from "../assets/roundPattern";

export default function SideMenu() {
  interface Category {
    id: number;
    name: string;
  }

  const [categories, setCategories] = useState<Category[]>([]);
  const [loading, setLoading] = useState(true);
  const [visiblePatterns, setVisiblePatterns] = useState<Set<number>>(
    new Set()
  );

  const containerRef = useRef<HTMLDivElement>(null);
  const patternRefs = useRef<Record<number, HTMLDivElement | null>>({});

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/categories");
        setCategories(res.data);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
    fetchCategories();
  }, []);

  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          const index = Number(entry.target.getAttribute("data-index"));
          if (entry.isIntersecting && !visiblePatterns.has(index)) {
            setVisiblePatterns((prev) => new Set(prev).add(index));
          }
        });
      },
      { threshold: 0.1 }
    );

    Object.values(patternRefs.current).forEach(
      (el) => el && observer.observe(el)
    );

    return () => observer.disconnect();
  }, [visiblePatterns]);

  const sortedCategories = [...categories].sort((a, b) =>
    a.name.localeCompare(b.name)
  );

  const patternInterval = 2200;
  const startOffset = 1000;

  useEffect(() => {
    const handleScroll = () => {
      const scrollY = window.scrollY;
      const patternsToShow: number[] = [];

      for (
        let pos = startOffset;
        pos < document.body.scrollHeight;
        pos += patternInterval
      ) {
        if (scrollY + window.innerHeight > pos) {
          patternsToShow.push(pos);
        }
      }

      setVisiblePatterns(new Set(patternsToShow));
    };

    window.addEventListener("scroll", handleScroll);
    handleScroll();
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  return (
    <div
      ref={containerRef}
      className="relative overflow-y-hidden  flex min-w-3/12 my-2 rounded items-start min-h-[calc(100vh-8rem)] bg-blue-300 opacity-80 gap-6 p-6 flex-col text-zinc-600"
    >
      {Array.from(visiblePatterns).map((pos, idx) => (
        <div
          key={idx}
          style={{ top: pos - containerRef.current!.offsetTop }}
          className="absolute pointer-events-none -left-145 top-pos fill-blue-50 opacity-60 scale-150"
        >
          <RoundPattern />
        </div>
      ))}
      <div
        style={{ fontFamily: "EB Garamond, serif" }}
        className="text-3xl bg-zinc-100 shadow p-3 rounded-2xl text-zinc-700 select-none z-10 hover:text-zinc-900 hover:cursor-pointer"
      >
        Dodaj wpis +
      </div>

      <div className="flex flex-col items-start w-full p-3 bg-blue-50 rounded z-10">
        <div
          style={{ fontFamily: "EB Garamond, serif" }}
          className="text-2xl text-zinc-700 select-none"
        >
          Kategorie
        </div>
        <div className="w-full h-px bg-blue-200 my-2"></div>

        <div
          style={{ fontFamily: "EB Garamond, serif" }}
          className="flex flex-col gap-1 pl-2 pt-2 text-lg select-none"
        >
          {loading ? (
            <div>Ładowanie kategorii...</div>
          ) : sortedCategories.length > 0 ? (
            sortedCategories.map((cat) => (
              <div
                onClick={() => {}}
                key={cat.id}
                className="hover:text-zinc-900 hover:cursor-pointer"
              >
                {cat.name.charAt(0).toUpperCase() + cat.name.slice(1)}
              </div>
            ))
          ) : (
            <div>Brak kategorii</div>
          )}
        </div>
      </div>
    </div>
  );
}
